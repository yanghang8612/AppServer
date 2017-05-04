package com.huachuang.server.controller;

import com.huachuang.server.entity.ApplyCreditCard;
import com.huachuang.server.entity.ApplyLoan;
import com.huachuang.server.service.FinancialService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Asuka on 2017/4/17.
 */

@Controller
@RequestMapping(value = "/Financial")
public class FinancialController {

    @Resource
    private FinancialService financialService;

    @ResponseBody
    @RequestMapping(value = "/ApplyLoan", method = RequestMethod.POST)
    private Map<String, String> applyLoan(
            @RequestParam long userId,
            @RequestParam String houseAddress,
            @RequestParam String housePropertyCard,
            @RequestParam String houseLandSources,
            @RequestParam String houseType,
            @RequestParam String houseBuildYear,
            @RequestParam String houseBuildArea,
            @RequestParam boolean houseOwnedByOthers,
            @RequestParam boolean houseIsMortgaged,
            @RequestParam boolean houseBorrowerIsOwner,
            @RequestParam String houseHandingTime,
            @RequestParam String borrowerName,
            @RequestParam String borrowerPhoneNumber,
            @RequestParam String borrowerAmount,
            @RequestParam String borrowerMarriage,
            @RequestParam String borrowerAddress,
            @RequestParam String borrowerDetailedAddress) {

        ApplyLoan applyLoan = new ApplyLoan();
        applyLoan.setUserId(userId);
        applyLoan.setHouseAddress(houseAddress);
        applyLoan.setHousePropertyCard(housePropertyCard);
        applyLoan.setHouseLandSources(houseLandSources);
        applyLoan.setHouseType(houseType);
        applyLoan.setHouseBuildYear(houseBuildYear);
        applyLoan.setHouseBuildArea(houseBuildArea);
        applyLoan.setHouseOwnedByOthers(houseOwnedByOthers);
        applyLoan.setHouseIsMortgaged(houseIsMortgaged);
        applyLoan.setHouseBorrowerIsOwner(houseBorrowerIsOwner);
        applyLoan.setHouseHandingTime(houseHandingTime);
        applyLoan.setBorrowerName(borrowerName);
        applyLoan.setBorrowerPhoneNumber(borrowerPhoneNumber);
        applyLoan.setBorrowerAmount(borrowerAmount);
        applyLoan.setBorrowerMarriage(borrowerMarriage);
        applyLoan.setBorrowerAddress(borrowerAddress);
        applyLoan.setBorrowerDetailedAddress(borrowerDetailedAddress);
        return financialService.createApplyLoan(applyLoan);
    }

    @ResponseBody
    @RequestMapping(value = "/UpdateLoanState", method = RequestMethod.POST)
    private Map<String, String> updateLoanState(
            @RequestParam long id,
            @RequestParam byte state) {

        return financialService.updateApplyLoan(id, state);
    }

    @ResponseBody
    @RequestMapping(value = "/ApplyCreditCard", method = RequestMethod.POST)
    private Map<String, String> applyCreditCard(
            @RequestParam long userId,
            @RequestParam byte bankId,
            @RequestParam String userName,
            @RequestParam String userPhoneNumber,
            @RequestParam String userCompany) {

        ApplyCreditCard applyCreditCard = new ApplyCreditCard();
        applyCreditCard.setUserId(userId);
        applyCreditCard.setApplyBank(bankId);
        applyCreditCard.setApplyUserName(userName);
        applyCreditCard.setApplyUserPhoneNumber(userPhoneNumber);
        applyCreditCard.setApplyUserCompany(userCompany);
        return financialService.createApplyCreditCard(applyCreditCard);
    }
}
