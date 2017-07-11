package com.huachuang.server.controller;

import com.huachuang.server.CommonUtils;
import com.huachuang.server.entity.UserCertificationInfo;
import com.huachuang.server.entity.UserDebitCard;
import com.huachuang.server.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
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
            @RequestParam String userEMail,
            @RequestParam String userAddress,
            @RequestParam char userSex) {

        UserCertificationInfo certificationInfo = new UserCertificationInfo();
        certificationInfo.setUserId(userID);
        certificationInfo.setUserName(userName);
        certificationInfo.setUserSpell(userSpell);
        certificationInfo.setUserIdentityCard(userIdentityCard);
        certificationInfo.setUserEMail(userEMail);
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
            @RequestParam String userEMail,
            @RequestParam String userAddress,
            @RequestParam char userSex) {

        UserCertificationInfo certificationInfo = new UserCertificationInfo();
        certificationInfo.setId(id);
        certificationInfo.setUserId(userID);
        certificationInfo.setUserName(userName);
        certificationInfo.setUserSpell(userSpell);
        certificationInfo.setUserIdentityCard(userIdentityCard);
        certificationInfo.setUserEMail(userEMail);
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

    @ResponseBody
    @RequestMapping(value = "/GetGlobalVariable", method = RequestMethod.POST)
    public Map<String, String> getGlobalVariable() {

        Map<String, String> result = new HashMap<>();
        result.put("VIP_FEE", CommonUtils.getProperty("VIP_FEE"));
        result.put("LEVEL_ONE_AGENT_FEE", CommonUtils.getProperty("LEVEL_ONE_AGENT_FEE"));
        result.put("LEVEL_TWO_AGENT_FEE", CommonUtils.getProperty("LEVEL_TWO_AGENT_FEE"));
        result.put("SERVICE_PHONE_NUMBER", CommonUtils.getProperty("SERVICE_PHONE_NUMBER"));
        result.put("COMMON_RATE", CommonUtils.getProperty("COMMON_RATE"));
        result.put("VIP_RATE", CommonUtils.getProperty("VIP_RATE"));
        result.put("AGENT_RATE", CommonUtils.getProperty("AGENT_RATE"));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateGlobalVariable", method = RequestMethod.POST)
    public Map<String, String> updateGlobalVariable(
            @RequestParam int vipFee,
            @RequestParam int levelOneAgentFee,
            @RequestParam int levelTwoAgentFee,
            @RequestParam String servicePhoneNumber,
            @RequestParam String commonRate,
            @RequestParam String vipRate,
            @RequestParam String agentRate,
            @RequestParam String vipRegisterShareProfitRatio,
            @RequestParam String agentRegisterShareProfitRatio,
            @RequestParam String levelOneAgentCommonProfitRatio,
            @RequestParam String levelTwoAgentCommonProfitRatio,
            @RequestParam String vipLevelOneProfitRatio,
            @RequestParam String vipLevelTwoProfitRatio,
            @RequestParam String vipLevelThreeProfitRatio,
            @RequestParam String commonLevelOneProfitRatio,
            @RequestParam String commonLevelTwoProfitRatio,
            @RequestParam String commonLevelThreeProfitRatio
    ) {

        CommonUtils.setProperty("VIP_FEE", String.valueOf(vipFee));
        CommonUtils.setProperty("LEVEL_ONE_AGENT_FEE", String.valueOf(levelOneAgentFee));
        CommonUtils.setProperty("LEVEL_TWO_AGENT_FEE", String.valueOf(levelTwoAgentFee));
        CommonUtils.setProperty("SERVICE_PHONE_NUMBER", servicePhoneNumber);
        CommonUtils.setProperty("COMMON_RATE", convertRatioToString(commonRate));
        CommonUtils.setProperty("VIP_RATE", convertRatioToString(vipRate));
        CommonUtils.setProperty("AGENT_RATE", convertRatioToString(agentRate));
        CommonUtils.setProperty("VIP_REGISTER_SHARE_PROFIT_RATIO", convertRatioToString(vipRegisterShareProfitRatio));
        CommonUtils.setProperty("AGENT_REGISTER_SHARE_PROFIT_RATIO", convertRatioToString(agentRegisterShareProfitRatio));
        CommonUtils.setProperty("LEVEL_ONE_AGENT_COMMON_PROFIT_RATIO", convertRatioToString(levelOneAgentCommonProfitRatio));
        CommonUtils.setProperty("LEVEL_TWO_AGENT_COMMON_PROFIT_RATIO", convertRatioToString(levelTwoAgentCommonProfitRatio));
        CommonUtils.setProperty("VIP_LEVEL_ONE_PROFIT_RATIO", convertRatioToString(vipLevelOneProfitRatio));
        CommonUtils.setProperty("VIP_LEVEL_TWO_PROFIT_RATIO", convertRatioToString(vipLevelTwoProfitRatio));
        CommonUtils.setProperty("VIP_LEVEL_THREE_PROFIT_RATIO", convertRatioToString(vipLevelThreeProfitRatio));
        CommonUtils.setProperty("COMMON_LEVEL_ONE_PROFIT_RATIO", convertRatioToString(commonLevelOneProfitRatio));
        CommonUtils.setProperty("COMMON_LEVEL_TWO_PROFIT_RATIO", convertRatioToString(commonLevelTwoProfitRatio));
        CommonUtils.setProperty("COMMON_LEVEL_THREE_PROFIT_RATIO", convertRatioToString(commonLevelThreeProfitRatio));
        Map<String, String> result = new HashMap<>();
        result.put("Status", "true");
        result.put("Info", "参数修改成功");
        return result;
    }

    private String convertRatioToString(String ratio) {
        if (!ratio.contains(".")) {
            return ratio;
        }
        String intPart = ratio.split("\\.")[0];
        int decPart = Integer.valueOf(ratio.split("\\.")[1]);
        return intPart + (decPart == 0 ? "" : ("." + decPart));
    }
}
