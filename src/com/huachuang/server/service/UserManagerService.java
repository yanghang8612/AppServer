package com.huachuang.server.service;

import com.huachuang.server.CommonUtils;
import com.huachuang.server.dao.RecommendListDao;
import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.dao.UserTokenDao;
import com.huachuang.server.entity.RecommendList;
import com.huachuang.server.entity.User;
import com.huachuang.server.entity.UserToken;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Asuka on 2017/3/28.
 */

@Service
public class UserManagerService {

    @Resource
    private UserManagerDao userManagerDao;

    @Resource
    private RecommendListDao recommendListDao;

    @Resource
    private UserTokenDao userTokenDao;

    public Map<String, String> verifyPhoneNumber(String phoneNumber) {
        Map<String, String> result = new HashMap<>();
        if (userManagerDao.verifyPhoneNumber(phoneNumber)) {
            result.put("Status", "true");
            result.put("Info", "该手机号尚未注册");
        }
        else {
            result.put("Status", "false");
            result.put("Info", "该手机号已注册，请登录");
        }
        return result;
    }

    public Map<String, String> verifyInvitationCode(String invitationCode) {
        Map<String, String> result = new HashMap<>();
        if (userManagerDao.verifyInvitationCode(invitationCode)) {
            result.put("Status", "true");
            result.put("Info", "邀请码验证通过");
        }
        else {
            result.put("Status", "false");
            result.put("Info", "邀请码验证失败");
        }
        return result;
    }

    public Map<String, String> verifyRecommenderID(String recommendID) {
        Map<String, String> result = new HashMap<>();
        if (userManagerDao.verifyRecommenderID(recommendID)) {
            result.put("Status", "true");
            result.put("Info", "推荐人验证通过");
        }
        else {
            result.put("Status", "false");
            result.put("Info", "该推荐人不存在");
        }
        return result;
    }

    public Map<String, String> register(String phoneNumber, String invitationCode, String recommenderID, String password){

        String generatedInvitationCode;
        while (true) {
            generatedInvitationCode = CommonUtils.generateInvitationCode();
            if (!userManagerDao.verifyInvitationCode(generatedInvitationCode)) {
                break;
            }
        }
        User user = new User(phoneNumber, password, generatedInvitationCode, userManagerDao.findUserByInvitationCode(invitationCode).getUserId());
        long userID = userManagerDao.create(user);
        if (recommenderID != null && !recommenderID.equals("")) {
            recommendListDao.create(new RecommendList(userManagerDao.findUserByPhoneNumber(recommenderID).getUserId(), userID));
        }
        Map<String, String> result = new HashMap<>();
        result.put("Status", "true");
        result.put("Info", "注册成功");
        return result;
    }
//
//    /*
//        登录
//     */
//    public Object login(String account, String password, HttpServletRequest request){
//        Map<String, Object> map = new HashMap<String, Object>();
//        UserInfo userInfo = new UserInfo();
//        UserInfo userInfoRet = new UserInfo();
//        UserInfoDao userInfoDao = new UserInfoDao();
//        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
//
//        final int userID;
//        String token = "";
//
//        // 将account解析为手机号 尝试登录
//        userInfo.setPhoneNum(account);
//        userInfo.setPassword(password);
//        userInfoList = userInfoDao.Retrieve(userInfo);
//        if (userInfoList.size() != 0){
//            userInfoRet = userInfoList.get(0);
//            userInfoRet.setPassword("");
//            String picPath = userInfoRet.gethPicPath();
//            String[] picPathArray = picPath.split("/");
//            String picName = picPathArray[picPathArray.length - 1];
//            userInfoRet.sethPicPath("");
//            if(picName != null && !picName.isEmpty()) {
//                userInfoRet.sethPicPath(Define.serverName + "/UserManage/DownloadHPic?phoneNum=" + userInfoRet.getPhoneNum() + "&picName=" + picName);
//            }
//            userID = userInfoRet.getUserID();
//            token = UpdateToken(userID);
//            map.put("Status", "true");
//            map.put("Info", "登录成功");
//            map.put("User", userInfoRet);
//            map.put("Token", token);
//            Define.onlineUser.add(String.valueOf(userID));
//            Executors.newSingleThreadExecutor().execute(new Runnable() {
//                public void run() {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                        JPushService.getInstance().pushMessagesWhenLogin(String.valueOf(userID));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//            return map;
//        }
//
//        // 将account解析为邮箱 尝试登录
//        userInfo.setPhoneNum(null);
//        userInfo.setEmail(account);
//        userInfoList = userInfoDao.Retrieve(userInfo);
//        if (userInfoList.size() != 0){
//            userInfoRet = userInfoList.get(0);
//            userInfoRet.setPassword("");
//            String picPath = userInfoRet.gethPicPath();
//            String[] picPathArray = picPath.split("/");
//            String picName = picPathArray[picPathArray.length - 1];
//            userInfoRet.sethPicPath("");
//            if(picName != null && !picName.isEmpty()) {
//                userInfoRet.sethPicPath(Define.serverName + "/UserManage/DownloadHPic?phoneNum=" + userInfoRet.getPhoneNum() + "&picName=" + picName);
//            }
//            userID = userInfoRet.getUserID();
//            token = UpdateToken(userID);
//            map.put("Status", "true");
//            map.put("Info", "登录成功");
//            map.put("User", userInfoRet);
//            map.put("Token", token);
//            Define.onlineUser.add(String.valueOf(userID));
//            Executors.newSingleThreadExecutor().execute(new Runnable() {
//                public void run() {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                        JPushService.getInstance().pushMessagesWhenLogin(String.valueOf(userID));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//            return map;
//        }
//
//        map.put("Status", "false");
//        map.put("Info", "登录失败");
//        map.put("User", "");
//        map.put("Token", "");
//        return map;
//    }
//
//    /*
//        登出
//     */
//    public Object Logout(String userID, HttpServletRequest request){
//        if (Define.onlineUser.contains(userID)) {
//            Define.onlineUser.remove(userID);
//        }
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("Info", "登出成功");
//        return map;
//    }
//
//    /*
//        验证token是否有效
//     */
//    public Object VerifyToken(String userID, String token, HttpServletRequest request){
//        Map<String, Object> map = new HashMap<String, Object>();
//        UserInfo userInfo = new UserInfo();
//        UserInfoDao userInfoDao = new UserInfoDao();
//
//        map.put("Status", "false");
//        map.put("Info", "");
//        map.put("User", "");
//        map.put("Token", "");
//
//        UserInfo userInfoRet;
//        List<UserInfo> userInfoList;
//        userInfo.setUserID(Integer.parseInt(userID));
//        userInfoList = userInfoDao.Retrieve(userInfo);
//        if (userInfoList.size() != 0){
//            userInfoRet = userInfoList.get(0);
//            userInfoRet.setPassword("");
//            String picPath = userInfoRet.gethPicPath();
//            String[] picPathArray = picPath.split("/");
//            String picName = picPathArray[picPathArray.length - 1];
//            userInfoRet.sethPicPath("");
//            if(picName != null && !picName.isEmpty()) {
//                userInfoRet.sethPicPath(Define.serverName + "/UserManage/DownloadHPic?phoneNum=" + userInfoRet.getPhoneNum() + "&picName=" + picName);
//            }
//        }
//        else {
//            map.put("Info", "用户不合法");
//            return map;
//        }
//
//        UserToken userToken = new UserToken();
//        UserTokenDao userTokenDao = new UserTokenDao();
//        List<UserToken> userTokenList = new ArrayList<UserToken>();
//
//        userToken.setUserID(Integer.parseInt(userID));
//        userTokenList = userTokenDao.Retrieve(userToken);
//        if (userTokenList.size() == 0){
//            map.put("Info", "用户与token不对应");
//            return map;
//        }
//        userToken = userTokenList.get(0);
//        String curTm = String.valueOf(System.currentTimeMillis());
//        if (token.equals(userToken.getToken()) && Long.parseLong(curTm) < Long.parseLong(userToken.getTokenExprTm())){
//            map.put("Status", "true");
//            map.put("Info", "用户token合法");
//            map.put("User", userInfoRet);
//            map.put("Token", token);
//            return map;
//        }
//        map.put("Info", "用户token过期");
//        return map;
//    }
//
//    /*
//        通过用户ID判断用户是否存在
//     */
//    public Boolean CheckUserExistByID(int userID){
//        UserInfo userInfo = new UserInfo();
//        UserInfoDao userInfoDao = new UserInfoDao();
//        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
//        userInfo.setUserID(userID);
//        userInfoList = userInfoDao.Retrieve(userInfo);
//        return userInfoList.size() != 0;
//    }
//
//    /*
//        通过userID获取个人信息
//     */
//    public UserInfo GetUserInfoByUserID(String userID){
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserID(Integer.parseInt(userID));
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        List<UserInfo> userInfoList = userInfoDao.Retrieve(userInfo);
//        if (userInfoList.size() == 1){
//            UserInfo userInfoRet = userInfoList.get(0);
//            String picPath = userInfoRet.gethPicPath();
//            String[] picPathArray = picPath.split("/");
//            String picName = picPathArray[picPathArray.length - 1];
//            userInfoRet.sethPicPath("");
//            if(picName != null && !picName.isEmpty()) {
//                userInfoRet.sethPicPath(Define.serverName + "/UserManage/DownloadHPic?phoneNum=" + userInfoRet.getPhoneNum() + "&picName=" + picName);
//            }
//            return userInfoRet;
//        }
//        return null;
//    }
//
//    /*
//        通过手机号获取个人信息
//     */
//    public UserInfo GetUserInfoByPhoneNum(String phoneNum){
//        // 根据参数构造UserInfo对象
//        UserInfo userInfo = new UserInfo();
//        UserInfo userInfoRet = new UserInfo();
//        userInfo.setPhoneNum(phoneNum);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
//        userInfoList = userInfoDao.Retrieve(userInfo);
//        if (userInfoList.size() == 1){
//            userInfoRet = userInfoList.get(0);
////            userInfoRet.setPassword("");
//            String picPath = userInfoRet.gethPicPath();
//            String[] picPathArray = picPath.split("/");
//            String picName = picPathArray[picPathArray.length - 1];
//            userInfoRet.sethPicPath("");
//            if(picName != null && !picName.isEmpty()) {
//                userInfoRet.sethPicPath(Define.serverName + "/UserManage/DownloadHPic?phoneNum=" + userInfoRet.getPhoneNum() + "&picName=" + picName);
//            }
//            return userInfoRet;
//        }
//        return null;
//    }
//
//    /*
//        通过邮箱获取个人信息
//     */
//    public UserInfo GetUserInfoByEmail(String email){
//        UserInfo userInfo = new UserInfo();
//        UserInfo userInfoRet = new UserInfo();
//        userInfo.setEmail(email);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
//        userInfoList = userInfoDao.Retrieve(userInfo);
//        if (userInfoList.size() == 1){
//            userInfoRet = userInfoList.get(0);
////            userInfoRet.setPassword("");
//            String picPath = userInfoRet.gethPicPath();
//            String[] picPathArray = picPath.split("/");
//            String picName = picPathArray[picPathArray.length - 1];
//            userInfoRet.sethPicPath("");
//            if(picName != null && !picName.isEmpty()) {
//                userInfoRet.sethPicPath(Define.serverName + "/UserManage/DownloadHPic?phoneNum=" + userInfoRet.getPhoneNum() + "&picName=" + picName);
//            }
//            return userInfoRet;
//        }
//        return null;
//    }
//
//    /*
//        通过昵称获取个人信息
//     */
//    public UserInfo GetUserInfoByName(String name){
//        UserInfo userInfo = new UserInfo();
//        UserInfo userInfoRet = new UserInfo();
//        userInfo.setName(name);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
//        userInfoList = userInfoDao.Retrieve(userInfo);
//        if (userInfoList.size() == 1){
//            userInfoRet = userInfoList.get(0);
////            userInfoRet.setPassword("");
//            String picPath = userInfoRet.gethPicPath();
//            String[] picPathArray = picPath.split("/");
//            String picName = picPathArray[picPathArray.length - 1];
//            userInfoRet.sethPicPath("");
//            if(picName != null && !picName.isEmpty()) {
//                userInfoRet.sethPicPath(Define.serverName + "/UserManage/DownloadHPic?phoneNum=" + userInfoRet.getPhoneNum() + "&picName=" + picName);
//            }
//            return userInfoRet;
//        }
//        return null;
//    }
//
//    /*
//        编辑昵称
//     */
//    public Boolean EditName(String phoneNum, String name){
//        // 根据参数构造UserInfo对象
//        UserInfo userInfo = GetUserInfoByPhoneNum(phoneNum);
//        if (userInfo == null){
//            return false;
//        }
//        userInfo.setName(name);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        userInfoDao.Update(userInfo);
//        return true;
//    }
//
//    /*
//        编辑密码
//     */
//    public Boolean EditPassword(String phoneNum, String oldPassword, String newPassword){
//        UserInfo userInfo = GetUserInfoByPhoneNum(phoneNum);
//        if (!oldPassword.equals(userInfo.getPassword())){
//            return false;
//        }
//        userInfo.setPassword(newPassword);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        userInfoDao.Update(userInfo);
//        return true;
//    }
//
//    /*
//        编辑性别
//     */
//    public Boolean EditSex(String phoneNum, String sex){
//        UserInfo userInfo = GetUserInfoByPhoneNum(phoneNum);
//        if (userInfo == null){
//            return false;
//        }
//        userInfo.setSex(sex);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        userInfoDao.Update(userInfo);
//        return true;
//    }
//
//    /*
//        编辑公司
//     */
//    public Boolean EditCompany(String phoneName, String company){
//        UserInfo userInfo = GetUserInfoByPhoneNum(phoneName);
//        if (userInfo == null){
//            return false;
//        }
//        userInfo.setCompany(company);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        userInfoDao.Update(userInfo);
//        return true;
//    }
//
//    /*
//        编辑职位
//     */
//    public Boolean EditPost(String phoneNum, String post){
//        UserInfo userInfo = GetUserInfoByPhoneNum(phoneNum);
//        if (userInfo == null){
//            return false;
//        }
//        userInfo.setPost(post);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        userInfoDao.Update(userInfo);
//        return true;
//    }
//
//    /*
//        编辑常住地
//     */
//    public Boolean EditResidence(String phoneNum, String residence){
//        UserInfo userInfo = GetUserInfoByPhoneNum(phoneNum);
//        if (userInfo == null){
//            return false;
//        }
//        userInfo.setResidence(residence);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        userInfoDao.Update(userInfo);
//        return true;
//    }
//
//    /*
//        编辑邮箱
//     */
//    public Boolean EditEmail(String phoneName, String email){
//        UserInfo userInfo = GetUserInfoByPhoneNum(phoneName);
//        if (userInfo == null){
//            return false;
//        }
//        userInfo.setEmail(email);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        userInfoDao.Update(userInfo);
//        return true;
//    }
//
//    /*
//        编辑头像图片位置
//     */
//    public Boolean EditHPic(String phoneName, String picPath){
//        UserInfo userInfo = GetUserInfoByPhoneNum(phoneName);
//        if (userInfo == null){
//            return false;
//        }
//        userInfo.sethPicPath(picPath);
//
//        UserInfoDao userInfoDao = new UserInfoDao();
//        userInfoDao.Update(userInfo);
//        return true;
//    }
//
//    /*
//        下载头像
//     */
//    public BufferedInputStream DownloadHPic(String phoneNum){
//        UserInfo userInfo = new UserInfo();
////        userInfo = GetUserInfoByPhoneNum(phoneNum);
//
//        userInfo.setPhoneNum(phoneNum);
//        UserInfoDao userInfoDao = new UserInfoDao();
//        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
//        userInfoList = userInfoDao.Retrieve(userInfo);
//        if (userInfoList.size() == 1){
//            userInfo = userInfoList.get(0);
//        }
//
//        if (userInfo != null){
//            String picPath = userInfo.gethPicPath();
//            String picName = "default";
////            String tmp[] = picPath.split("\\\\"); // 2016-03-28
//            String tmp[] = picPath.split("/");
//            if (tmp.length > 1){
//                picName = tmp[tmp.length - 1];
//            }
//            File file = new File(picPath);
//            if (file.exists()){
//                try {
//                    FileInputStream fis = new FileInputStream(file);
//                    BufferedInputStream bis = new BufferedInputStream(fis);
//                    return bis;
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
//
//    /*
//        生成固定长度的token
//     */
//    public String GenToken(int length){
//        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
//        Random random = new Random();
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < length; i++) {
//            int number = random.nextInt(base.length());
//            sb.append(base.charAt(number));
//        }
//        return sb.toString();
//    }
//
//    /*
//        更新token以及过期时间
//        成功返回token 失败返回空字符串
//     */
//    public String UpdateToken(int userID){
//        int length = 12;
//        String token = GenToken(length);
//        String exprTm = String.valueOf(System.currentTimeMillis() + 604800000);
//
//        UserToken userToken = new UserToken();
//        UserTokenDao userTokenDao = new UserTokenDao();
//        List<UserToken> userTokenList = new ArrayList<UserToken>();
//
//        userToken.setUserID(userID);
//        userTokenList = userTokenDao.Retrieve(userToken);
//        if (userTokenList == null || userTokenList.size() == 0){
//            userToken.setToken(token);
//            userToken.setTokenExprTm(exprTm);
//            if (userTokenDao.Create(userToken)){
//                return token;
//            }
//        }
//        else {
//            userToken = userTokenList.get(0);
//            userToken.setToken(token);
//            userToken.setTokenExprTm(exprTm);
//            if (userTokenDao.Update(userToken)){
//                return token;
//            }
//        }
//        return "";
//    }
//
//    public static void main(String[] args){
//        UserManageService userManageService = new UserManageService();
//        userManageService.UpdateToken(1);
//        userManageService.DownloadHPic("15201614717");
//    }
}
