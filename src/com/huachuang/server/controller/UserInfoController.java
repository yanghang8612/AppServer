package com.huachuang.server.controller;

import com.huachuang.server.entity.UserCertificationInfo;
import com.huachuang.server.service.UserInfoService;
import com.huachuang.server.service.UserManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Asuka on 2017/4/4.
 */

@Controller
@RequestMapping("/UserInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value = "/CreateCertificationInfo", method = RequestMethod.POST)
    public Map<String, Object> register(
            @RequestParam long userID,
            @RequestParam String userName,
            @RequestParam String userSpell,
            @RequestParam String userIdentityCard,
            @RequestParam char userSex) {

        UserCertificationInfo certificationInfo = new UserCertificationInfo();
        certificationInfo.setUserId(userID);
        certificationInfo.setUserName(userName);
        certificationInfo.setUserSpell(userSpell);
        certificationInfo.setUserIdentityCard(userIdentityCard);
        certificationInfo.setUserSex(userSex);
        return userInfoService.createUserCertificationInfo(certificationInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateCertificationInfo", method = RequestMethod.POST)
    public Map<String, Object> register(
            @RequestParam long id,
            @RequestParam long userID,
            @RequestParam String userName,
            @RequestParam String userSpell,
            @RequestParam String userIdentityCard,
            @RequestParam char userSex) {

        UserCertificationInfo certificationInfo = new UserCertificationInfo();
        certificationInfo.setId(id);
        certificationInfo.setUserId(userID);
        certificationInfo.setUserName(userName);
        certificationInfo.setUserSpell(userSpell);
        certificationInfo.setUserIdentityCard(userIdentityCard);
        certificationInfo.setUserSex(userSex);
        return userInfoService.updateUserCertificationInfo(certificationInfo);
    }
}
