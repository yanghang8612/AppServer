package com.huachuang.server.controller;

import com.huachuang.server.entity.UserBankCard;
import com.huachuang.server.service.UserBankCardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Asuka on 2017/4/19.
 */

@Controller
@RequestMapping(value = "/BankCardManager")
public class BankCardManagerController {

    @Resource
    private UserBankCardService userBankCardService;

    @ResponseBody
    @RequestMapping(value = "/AddBankCard", method = RequestMethod.POST)
    private Map<String, String> addBankCard(
            @RequestParam long userID,
            @RequestParam String ownerName,
            @RequestParam String cardNumber,
            @RequestParam String cardType,
            @RequestParam String bankName,
            @RequestParam String phoneNumber
    ) {
        UserBankCard card = new UserBankCard();
        card.setUserId(userID);
        card.setOwnerName(ownerName);
        card.setCardNumber(cardNumber);
        card.setCardType(cardType);
        card.setBankName(bankName);
        card.setPhoneNumber(phoneNumber);
        return userBankCardService.addBankCard(card);
    }

    @ResponseBody
    @RequestMapping(value = "/GetAllBankCards", method = RequestMethod.POST)
    private Map<String, Object> getAllBankCards(
            @RequestParam long userID
    ) {
        return userBankCardService.getAllBankCards(userID);
    }
}
