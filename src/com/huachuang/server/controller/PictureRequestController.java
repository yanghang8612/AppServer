package com.huachuang.server.controller;

import com.huachuang.server.service.PictureRequestService;
import com.huachuang.server.service.UserManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asuka on 2017/3/31.
 */

@Controller
@RequestMapping("/Picture")
public class PictureRequestController {

    @Resource
    private UserManagerService userManagerService;

    @Resource
    private PictureRequestService pictureRequestService;

    @ResponseBody
    @RequestMapping(value = "/UploadHeader", method = RequestMethod.POST)
    public Map<String, String> uploadHeader(
            HttpServletRequest request,
            @RequestParam long userID,
            @RequestParam CommonsMultipartFile header){

        Map<String, String> result = new HashMap<>();
        String path = request.getSession().getServletContext().getRealPath("/resources/header/");
        if (!header.isEmpty()) {
            try {
                header.transferTo(new File(path + userID + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                result.put("Status", "false");
                result.put("Info", "头像设置失败");
                return result;
            }
            userManagerService.setUserHeaderState(userID);
        }
        result.put("Status", "true");
        result.put("Info", "头像设置成功");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/UploadIdentifyCard", method = RequestMethod.POST)
    public Map<String, String> uploadIdentifyCard(
            @RequestParam String phoneNumber,
            @RequestParam CommonsMultipartFile front,
            @RequestParam CommonsMultipartFile back,
            @RequestParam CommonsMultipartFile handing){

        Map<String, String> result = new HashMap<>();
        String savePath = "D:/PalmTouchServer/" + phoneNumber + "/identify_card/";
        File saveFolder = new File(savePath);
        if (!saveFolder.exists() && !saveFolder.isDirectory()) {
            saveFolder.mkdirs();
        }
        if (!front.isEmpty()) {
            try {
                front.transferTo(new File(savePath + "front.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                result.put("Status", "false");
                result.put("Info", "上传身份证正面失败");
                return result;
            }
        }
        if (!back.isEmpty()) {
            try {
                back.transferTo(new File(savePath + "back.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                result.put("Status", "false");
                result.put("Info", "上传身份证反面失败");
                return result;
            }
        }
        if (!handing.isEmpty()) {
            try {
                handing.transferTo(new File(savePath + "handing.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                result.put("Status", "false");
                result.put("Info", "上传手持证件照失败");
                return result;
            }
        }
        result.put("Status", "true");
        result.put("Info", "上传身份证正反面及手持证件照成功");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/UploadDebitCard", method = RequestMethod.POST)
    public Map<String, String> uploadDebitCard(
            @RequestParam String phoneNumber,
            @RequestParam CommonsMultipartFile front,
            @RequestParam CommonsMultipartFile back){

        Map<String, String> result = new HashMap<>();
        String savePath = "D:/PalmTouchServer/" + phoneNumber + "/debit_card/";
        File saveFolder = new File(savePath);
        if (!saveFolder.exists() && !saveFolder.isDirectory()) {
            saveFolder.mkdirs();
        }
        if (!front.isEmpty()) {
            try {
                front.transferTo(new File(savePath + "front.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                result.put("Status", "false");
                result.put("Info", "上传结算卡正面失败");
                return result;
            }
        }
        if (!back.isEmpty()) {
            try {
                back.transferTo(new File(savePath + "back.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                result.put("Status", "false");
                result.put("Info", "上传结算卡反面失败");
                return result;
            }
        }
        result.put("Status", "true");
        result.put("Info", "上传结算卡正反面成功");
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/DownloadHeader", method = RequestMethod.POST)
    public void DownloadHPic(
            HttpServletResponse response,
            @RequestParam String userID){

    }
}
