package com.huachuang.server;

import java.io.*;
import java.util.Properties;

/**
 * Created by Asuka on 2017/3/30.
 */
public class CommonUtils {

    private CommonUtils(){}

    private static String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String generateInvitationCode() {
        StringBuilder code = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            int index = ((int) (Math.random() * 100)) % base.length();
            code.append(base.charAt(index));
        }
        return code.toString();
    }

    private static Properties props;
    private static String filePath;
    static{
        filePath = CommonUtils.class.getClassLoader().getResource("/").getPath();
        filePath = filePath.substring(1, filePath.indexOf("classes")) + "server.properties";
        loadProps();
    }

    private synchronized static void loadProps(){
        props = new Properties();
        InputStream in = null;
        try {
            in = new FileInputStream(filePath);
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized static void storeProps(){
        OutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            props.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != out) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }

    public static void setProperty(String key, String value) {
        if(null == props) {
            loadProps();
        }
        props.setProperty(key, value);
        storeProps();
    }

    public static void main(String[] args) {
        System.out.println(generateInvitationCode());
        System.out.println(getProperty("VIP_FEE"));
    }
}
