package com.huachuang.server.controller;

import com.huachuang.server.service.WalletManagerService;
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
@RequestMapping(value = "/WalletManager")
public class WalletManagerController {

    @Resource
    private WalletManagerService walletManagerService;

    @ResponseBody
    @RequestMapping(value = "/GetWallet", method = RequestMethod.POST)
    private Map<String, String> getWallet(
            @RequestParam long userID) {

        return walletManagerService.getUserWallet(userID);
    }

    @ResponseBody
    @RequestMapping(value = "/GetWalletBalanceRecords", method = RequestMethod.POST)
    private Map<String, Object> getWalletBalanceRecords(
            @RequestParam long userID) {

        return walletManagerService.getWalletBalanceRecords(userID);
    }

    @ResponseBody
    @RequestMapping(value = "/GetWalletPointsRecords", method = RequestMethod.POST)
    private Map<String, Object> getWalletPointsRecords(
            @RequestParam long userID) {

        return walletManagerService.getWalletPointsRecords(userID);
    }
}
