package com.huachuang.server.controller;

import com.huachuang.server.entity.UserWithdraw;
import com.huachuang.server.service.UserManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

/**
 * Created by Asuka on 2017/3/19.
 */

@Controller
@RequestMapping("/UserManager")
public class UserManagerController {

    @Resource
    private UserManagerService userManagerService;

    @ResponseBody
    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public Map<String, String> register(
            @RequestParam String phoneNumber,
            @RequestParam String password,
            @RequestParam String identifyCode,
            @RequestParam byte shareType) {

        return userManagerService.register(phoneNumber, password, identifyCode, shareType);
    }

    @ResponseBody
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public Map<String, Object> login(
            @RequestParam String phoneNumber,
            @RequestParam String password) {

        return userManagerService.login(phoneNumber, password);
    }

    @ResponseBody
    @RequestMapping(value = "/Update", method = RequestMethod.POST)
    public Map<String, Object> update(
            @RequestParam long userID) {

        return userManagerService.update(userID);
    }

    @ResponseBody
    @RequestMapping(value = "/VerifyPhoneNumber", method = RequestMethod.POST)
    public Map<String, String> verifyPhoneNumber(
            @RequestParam String phoneNumber) {

        return userManagerService.verifyPhoneNumber(phoneNumber);
    }

    @ResponseBody
    @RequestMapping(value = "/VerifyInvitationCode", method = RequestMethod.POST)
    public Map<String, String> verifyInvitationCode(
            @RequestParam String invitationCode) {

        return userManagerService.verifyInvitationCode(invitationCode);
    }

    @ResponseBody
    @RequestMapping(value = "/VerifyRecommenderID", method = RequestMethod.POST)
    public Map<String, String> verifyRecommenderID(
            @RequestParam String recommenderID) {

        return userManagerService.verifyRecommenderID(recommenderID);
    }

    @ResponseBody
    @RequestMapping(value = "/ChangePassword", method = RequestMethod.POST)
    public Map<String, String> changePassword(
            @RequestParam long userID,
            @RequestParam String newPassword) {

        return userManagerService.changePassword(userID, newPassword);
    }

    @ResponseBody
    @RequestMapping(value = "/ResetPassword", method = RequestMethod.POST)
    public Map<String, String> resetPassword(
            @RequestParam String phoneNumber,
            @RequestParam String newPassword) {

        return userManagerService.resetPassword(phoneNumber, newPassword);
    }

    @ResponseBody
    @RequestMapping(value = "/ChangePhoneNumber", method = RequestMethod.POST)
    public Map<String, String> changePhoneNumber(
            HttpServletRequest request,
            @RequestParam long userID,
            @RequestParam String oldPhoneNumber,
            @RequestParam String newPhoneNumber) {

        Map<String, String> result = userManagerService.changePhoneNumber(userID, newPhoneNumber);
        if (result.get("Status").equals("true")) {
            File oldSaveFolder = new File("D:/PalmTouchServer/" + oldPhoneNumber + "/");
            File newSaveFolder = new File("D:/PalmTouchServer/" + newPhoneNumber + "/");
            if (oldSaveFolder.exists() && oldSaveFolder.isDirectory()) {
                oldSaveFolder.renameTo(newSaveFolder);
            }

            File oldPreviewFolder = new File(request.getSession().getServletContext().getRealPath("/resources/preview/") + oldPhoneNumber + "/");
            File newPreviewFolder = new File(request.getSession().getServletContext().getRealPath("/resources/preview/") + newPhoneNumber + "/");
            if (oldPreviewFolder.exists() && oldPreviewFolder.isDirectory()) {
                oldPreviewFolder.renameTo(newPreviewFolder);
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/AddAgent", method = RequestMethod.POST)
    public Map<String, String> addAgent(
            @RequestParam String phoneNumber,
            @RequestParam String password,
            @RequestParam byte type) {

        return userManagerService.addAgent(phoneNumber, password, type);
    }

    @ResponseBody
    @RequestMapping(value = "/DeleteUser", method = RequestMethod.POST)
    public Map<String, String> deleteUser(
            @RequestParam String phoneNumber) {

        return userManagerService.deleteUser(phoneNumber);
    }

    @ResponseBody
    @RequestMapping(value = "/GetSubUser", method = RequestMethod.POST)
    public Map<String, Object> getSubUser(
            @RequestParam long userID) {

        return userManagerService.getSubUser(userID);
    }

    @ResponseBody
    @RequestMapping(value = "/GetSubDirectUser", method = RequestMethod.POST)
    public Map<String, Object> getSubDirectUser(
            @RequestParam long userID) {

        return userManagerService.getSubDirectUser(userID);
    }

    @ResponseBody
    @RequestMapping(value = "/GetRecommendCount", method = RequestMethod.POST)
    public Map<String, String> getRecommendCount(
            @RequestParam long userID) {

        return userManagerService.getRecommendCount(userID);
}

    @ResponseBody
    @RequestMapping(value = "/GetRecommendRecord", method = RequestMethod.POST)
    public Map<String, Object> getRecommendRecord(
            @RequestParam long userID) {

        return userManagerService.getRecommendRecord(userID);
    }

    @ResponseBody
    @RequestMapping(value = "/CommitFeedback", method = RequestMethod.POST)
    public Map<String, String> commitFeedback(
            @RequestParam long userID,
            @RequestParam String message) {

        return userManagerService.commitFeedback(userID, message);
    }

    @ResponseBody
    @RequestMapping(value = "/GetUnreadFeedbackCount", method = RequestMethod.POST)
    public Map<String, String> getUnreadFeedbackCount() {

        return userManagerService.getUnreadFeedbackCount();
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateFeedbackState", method = RequestMethod.POST)
    public Map<String, String> updateFeedbackState(
            @RequestParam long id) {

        return userManagerService.updateFeedbackState(id);
    }

    @ResponseBody
    @RequestMapping(value = "/DeleteFeedback", method = RequestMethod.POST)
    public Map<String, String> deleteFeedback(
            @RequestParam long id) {

        return userManagerService.deleteFeedback(id);
    }
}
