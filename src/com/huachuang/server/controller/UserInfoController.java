package com.huachuang.server.controller;

import com.huachuang.server.entity.UserCertificationInfo;
import com.huachuang.server.entity.UserDebitCard;
import com.huachuang.server.service.UserInfoService;
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
    public Map<String, Object> createCertificationInfo(
            @RequestParam long userID,
            @RequestParam String userName,
            @RequestParam String userSpell,
            @RequestParam String userIdentityCard,
            @RequestParam String userAddress,
            @RequestParam char userSex) {

        UserCertificationInfo certificationInfo = new UserCertificationInfo();
        certificationInfo.setUserId(userID);
        certificationInfo.setUserName(userName);
        certificationInfo.setUserSpell(userSpell);
        certificationInfo.setUserIdentityCard(userIdentityCard);
        certificationInfo.setUserAddress(userAddress);
        certificationInfo.setUserSex(userSex);
        return userInfoService.createUserCertificationInfo(certificationInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateCertificationInfo", method = RequestMethod.POST)
    public Map<String, Object> updateCertificationInfo(
            @RequestParam long id,
            @RequestParam long userID,
            @RequestParam String userName,
            @RequestParam String userSpell,
            @RequestParam String userIdentityCard,
            @RequestParam String userAddress,
            @RequestParam char userSex) {

        UserCertificationInfo certificationInfo = new UserCertificationInfo();
        certificationInfo.setId(id);
        certificationInfo.setUserId(userID);
        certificationInfo.setUserName(userName);
        certificationInfo.setUserSpell(userSpell);
        certificationInfo.setUserIdentityCard(userIdentityCard);
        certificationInfo.setUserAddress(userAddress);
        certificationInfo.setUserSex(userSex);
        return userInfoService.updateUserCertificationInfo(certificationInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/CreateDebitCard", method = RequestMethod.POST)
    public Map<String, Object> createDebitCard(
            @RequestParam long userID,
            @RequestParam String ownerName,
            @RequestParam String cardNumber,
            @RequestParam String cardType,
            @RequestParam String headOffice,
            @RequestParam String branch,
            @RequestParam String province) {

        UserDebitCard debitCard = new UserDebitCard();
        debitCard.setUserId(userID);
        debitCard.setOwnerName(ownerName);
        debitCard.setCardNumber(cardNumber);
        debitCard.setCardType(cardType);
        debitCard.setHeadOffice(headOffice);
        debitCard.setBranch(branch);
        debitCard.setProvince(province);
        return userInfoService.createUserDebitCard(debitCard);
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateDebitCard", method = RequestMethod.POST)
    public Map<String, Object> updateDebitCard(
            @RequestParam long id,
            @RequestParam long userID,
            @RequestParam String ownerName,
            @RequestParam String cardNumber,
            @RequestParam String cardType,
            @RequestParam String headOffice,
            @RequestParam String branch,
            @RequestParam String province) {

        UserDebitCard debitCard = new UserDebitCard();
        debitCard.setId(id);
        debitCard.setUserId(userID);
        debitCard.setOwnerName(ownerName);
        debitCard.setCardNumber(cardNumber);
        debitCard.setCardType(cardType);
        debitCard.setHeadOffice(headOffice);
        debitCard.setBranch(branch);
        debitCard.setProvince(province);
        return userInfoService.updateUserDebitCard(debitCard);
    }

    @ResponseBody
    @RequestMapping(value = "/GetMobilePay", method = RequestMethod.POST)
    public Map<String, Object> getMobilePay(
            @RequestParam long userID) {

        return userInfoService.getUserMobilePayInfo(userID);
    }
}
