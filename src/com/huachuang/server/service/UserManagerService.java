package com.huachuang.server.service;

import com.huachuang.server.CommonUtils;
import com.huachuang.server.dao.RecommendListDao;
import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.dao.UserTokenDao;
import com.huachuang.server.dao.UserWalletDao;
import com.huachuang.server.entity.RecommendList;
import com.huachuang.server.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.*;

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

    @Resource
    private UserWalletDao userWalletDao;

    @Resource
    private UserInfoService userInfoService;

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
            result.put("Info", "邀请码无效，请检查");
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

    public Map<String, String> register(
            String phoneNumber,
            String password,
            String identifyCode,
            byte shareType
    ){
        String generatedInvitationCode;
        while (true) {
            generatedInvitationCode = CommonUtils.generateInvitationCode();
            if (!userManagerDao.verifyInvitationCode(generatedInvitationCode)) {
                break;
            }
        }
        long superiorID;
        if (identifyCode.length() == 6) {
            superiorID = userManagerDao.findUserByInvitationCode(identifyCode).getUserId();
        }
        else {
            superiorID = userManagerDao.findUserByPhoneNumber(identifyCode).getSuperiorUserId();
        }
        User user = new User(phoneNumber, password, generatedInvitationCode, superiorID);
        long userID = userManagerDao.create(user);
        if (identifyCode.length() == 11) {
            RecommendList record = new RecommendList();
            record.setType(shareType);
            record.setRecommenderId(userManagerDao.findUserByPhoneNumber(identifyCode).getUserId());
            record.setRecommendedId(userID);
            recommendListDao.create(record);
        }
        userWalletDao.create(userID);
        Map<String, String> result = new HashMap<>();
        result.put("Status", "true");
        result.put("Info", "注册成功");
        return result;
    }

    public Map<String, Object> login(String phoneNumber, String password){
        Map<String, Object> result = new HashMap<String, Object>();

        User user = userManagerDao.findUserByPhoneNumber(phoneNumber);
        if (user == null) {
            result.put("Status", "false");
            result.put("Info", "该手机号尚未注册");
        }
        else if (!user.getUserPassword().equals(password)) {
            result.put("Status", "false");
            result.put("Info", "密码错误,请检查后重试");
        }
        else {
            user.setLastLoginTime(Calendar.getInstance().getTime());
            userManagerDao.update(user);
            result.put("Status", "true");
            result.put("Info", "登录成功");
            result.put("User", user);
            if (user.isCertificationState()) {
                result.put("CertificationInfo", userInfoService.getUserCertificationInfo(user.getUserId()).get("CertificationInfo"));
            }
            if (user.isDebitCardState()) {
                result.put("DebitCard", userInfoService.getUserDebitCard(user.getUserId()).get("DebitCard"));
            }
        }
        return result;
    }

    public Map<String, String> changePassword(long userID, String newPassword) {
        Map<String, String> result = new HashMap<>();
        User user = userManagerDao.findUserByUserID(userID);
        if (user != null) {
            user.setUserPassword(newPassword);
            userManagerDao.update(user);
            result.put("Status", "true");
            result.put("Status", "密码修改成功");
        }
        else {
            result.put("Status", "false");
            result.put("Status", "密码修改失败");
        }
        return result;
    }

    public Map<String, Object> getSubUser(long userID) {
        Map<String, Object> result = new HashMap<>();
        User user = userManagerDao.findUserByUserID(userID);
        if (user != null) {
            if (user.getUserType() == 0) {
                result.put("Status", "true");
                result.put("Info", "普通及VIP用户没有下属用户");
            }
            else if (user.getUserType() == 3) {
                List<User> users = userManagerDao.findSubUsers(userID);
                result.put("Status", "true");
                result.put("Users", users);
            }
            else if (user.getUserType() == 2) {
                List<User> users = new ArrayList<>();
                List<User> levelThreeUsers = userManagerDao.findSubUsers(userID);
                if (levelThreeUsers != null) {
                    for (User levelThreeUser : levelThreeUsers) {
                        users.add(levelThreeUser);
                        if (levelThreeUser.getUserType() != 0) {
                            users.addAll(userManagerDao.findSubUsers(levelThreeUser.getUserId()));
                        }
                    }
                }
                result.put("Status", "true");
                result.put("Users", users);
            }
            else {
                List<User> users = new ArrayList<>();
                List<User> levelTwoUsers = userManagerDao.findSubUsers(userID);
                if (levelTwoUsers != null) {
                    for (User levelTwoUser : levelTwoUsers) {
                        users.add(levelTwoUser);
                        if (levelTwoUser.getUserType() != 0) {
                            List<User> levelThreeUsers = userManagerDao.findSubUsers(levelTwoUser.getUserId());
                            if (levelThreeUsers != null) {
                                for (User levelThreeUser : levelThreeUsers) {
                                    users.add(levelThreeUser);
                                    if (levelThreeUser.getUserType() != 0) {
                                        users.addAll(userManagerDao.findSubUsers(levelThreeUser.getUserId()));
                                    }
                                }
                            }
                        }
                    }
                }
                result.put("Status", "true");
                result.put("Users", users);
            }
        }
        else {
            result.put("Status", "false");
            result.put("Info", "查询下级用户出错");
        }
        return result;
    }

    public Map<String, String> addAgent(
            String superiorPhoneNumber,
            String phoneNumber,
            String password,
            byte type
    ) {
        Map<String, String> result = new HashMap<>();
        if (!userManagerDao.verifyPhoneNumber(phoneNumber)) {
            result.put("Status", "false");
            result.put("Info", "该手机号已被注册");
        }
        else {
            String generatedInvitationCode;
            while (true) {
                generatedInvitationCode = CommonUtils.generateInvitationCode();
                if (!userManagerDao.verifyInvitationCode(generatedInvitationCode)) {
                    break;
                }
            }
            User superiorAgent = userManagerDao.findUserByPhoneNumber(superiorPhoneNumber);
            User agent = new User();
            agent.setUserPhoneNumber(phoneNumber);
            agent.setUserPassword(password);
            agent.setUserType(type);
            agent.setInvitationCode(generatedInvitationCode);
            agent.setSuperiorUserId((type == 1) ? 0 : superiorAgent.getUserId());
            userManagerDao.create(agent);
            result.put("Status", "true");
            result.put("Info", "添加代理商成功");
        }
        return result;
    }

    public void setUserHeaderState(long userID) {
        User user = userManagerDao.findUserByUserID(userID);
        if (user != null) {
            user.setHeaderState(true);
            userManagerDao.update(user);
        }
    }

    public Map<String, String> getRecommendCount(long userID) {
        Map<String, String> result = new HashMap<>();
        List<RecommendList> baseList = recommendListDao.findRecommendRecordByUserID(userID);
        List<RecommendList> deriveList = new ArrayList<>();
        List<RecommendList> thirdList = new ArrayList<>();
        for (RecommendList record : baseList) {
            deriveList.addAll(recommendListDao.findRecommendRecordByUserID(record.getRecommendedId()));
        }
        for (RecommendList record : deriveList) {
            thirdList.addAll(recommendListDao.findRecommendRecordByUserID(record.getRecommendedId()));
        }
        result.put("Status", "true");
        result.put("Info", "查询成功");
        result.put("BaseCount", String.valueOf(baseList.size()));
        result.put("DeriveCount", String.valueOf(deriveList.size()));
        result.put("ThirdCount", String.valueOf(thirdList.size()));
        return result;
    }

    private class RecommendRecord implements Serializable {

        private String phoneNumber;
        private byte type;
        private Date recommendTime;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public byte getType() {
            return type;
        }

        public void setType(byte type) {
            this.type = type;
        }

        public Date getRecommendTime() {
            return recommendTime;
        }

        public void setRecommendTime(Date recommendTime) {
            this.recommendTime = recommendTime;
        }
    }

    public Map<String, Object> getRecommendRecord(long userID) {
        Map<String, Object> result = new HashMap<>();
        List<RecommendList> list = recommendListDao.findRecommendRecordByUserID(userID);
        List<RecommendRecord> records = new ArrayList<>();
        for (RecommendList item : list) {
            RecommendRecord record = new RecommendRecord();
            record.setPhoneNumber(userManagerDao.findUserByUserID(item.getRecommendedId()).getUserPhoneNumber());
            record.setType(item.getType());
            record.setRecommendTime(item.getRecommendTime());
            records.add(record);
        }
        result.put("Status", "true");
        result.put("Info", "查询成功");
        result.put("Records", records);
        return result;
    }

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
