package com.huachuang.server;

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

    public static void main(String[] args) {
        System.out.println(generateInvitationCode());
    }
}
