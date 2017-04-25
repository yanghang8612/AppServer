package com.huachuang.server.controller;

import com.huachuang.server.service.UserManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
            @RequestParam String identifyCode,
            @RequestParam String password) {

        return userManagerService.register(phoneNumber, identifyCode, password);
    }

    @ResponseBody
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public Map<String, Object> login(
            @RequestParam String phoneNumber,
            @RequestParam String password) {

        return userManagerService.login(phoneNumber, password);
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
    public Map<String, String> verifyRecommenderID(
            @RequestParam long userID,
            @RequestParam String newPassword) {

        return userManagerService.changePassword(userID, newPassword);
    }

    @ResponseBody
    @RequestMapping(value = "/AddAgent", method = RequestMethod.POST)
    public Map<String, String> addAgent(
            @RequestParam String superiorPhoneNumber,
            @RequestParam String phoneNumber,
            @RequestParam String password,
            @RequestParam byte type) {

        return userManagerService.addAgent(superiorPhoneNumber, phoneNumber, password, type);
    }

    @ResponseBody
    @RequestMapping(value = "/GetSubUser", method = RequestMethod.POST)
    public Map<String, Object> getSubUser(
            @RequestParam long userID) {

        return userManagerService.getSubUser(userID);
    }

    @ResponseBody
    @RequestMapping(value = "/GetRecommendCount", method = RequestMethod.POST)
    public Map<String, String> getRecommendCount(
            @RequestParam long userID) {

        return userManagerService.getRecommendCount(userID);
    }
}
