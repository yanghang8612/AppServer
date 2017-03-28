package com.huachuang.server.controller;

import com.huachuang.server.dao.UserManagerDao;
import com.huachuang.server.service.UserManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Asuka on 2017/3/19.
 */

@Controller
@RequestMapping("/UserManager")
public class UserManagerController {

    @Resource
    private UserManagerService userManagerService;

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String register() {
        return "Login";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public @ResponseBody String login() {
        return String.valueOf(userManagerService.getUserCountByPhoneNumber("18511838501"));
    }
}
