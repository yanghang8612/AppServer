package com.huachuang.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Asuka on 2017/3/19.
 */

@Controller
@RequestMapping("/UserManager")
public class UserManagerController {

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String register() {
        return "Login";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public @ResponseBody String login() {
        return "shabi";
    }
}
