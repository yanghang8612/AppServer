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

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public String register() {
        return "Login";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public @ResponseBody String login() {
        return String.valueOf(userManagerService.getUserCountByPhoneNumber("18511838501"));
    }

    @RequestMapping(value = "/VerifyInvitationCode", method = RequestMethod.POST)
    public @ResponseBody Object verifyInvitationCode(@RequestParam String invitationCode) {
        return userManagerService.verifyInvitationCode(invitationCode);
    }
}
