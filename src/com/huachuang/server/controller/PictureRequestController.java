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
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
            HttpServletRequest request,
            @RequestParam String phoneNumber,
            @RequestParam(required = false) CommonsMultipartFile front,
            @RequestParam(required = false) CommonsMultipartFile back,
            @RequestParam(required = false) CommonsMultipartFile handing){

        Map<String, String> result = new HashMap<>();

        String savePath = "D:/PalmTouchServer/" + phoneNumber + "/identify_card/";
        File saveFolder = new File(savePath);
        if (!saveFolder.exists() && !saveFolder.isDirectory()) {
            saveFolder.mkdirs();
        }

        String previewPath = request.getSession().getServletContext().getRealPath("/resources/preview/") + phoneNumber + "/identify/";
        File previewFolder = new File(previewPath);
        if (!previewFolder.exists() && !previewFolder.isDirectory()) {
            previewFolder.mkdirs();
        }

        if (front != null && !front.isEmpty()) {
            try {
                front.transferTo(new File(savePath + "front.jpg"));
                resizeImage(savePath + "front.jpg", previewPath + "front.jpg", 240, 240);
            } catch (IOException e) {
                e.printStackTrace();
                result.put("Status", "false");
                result.put("Info", "上传身份证正面失败");
                return result;
            }
        }

        if (back != null && !back.isEmpty()) {
            try {
                back.transferTo(new File(savePath + "back.jpg"));
                resizeImage(savePath + "back.jpg", previewPath + "back.jpg", 240, 240);
            } catch (IOException e) {
                e.printStackTrace();
                result.put("Status", "false");
                result.put("Info", "上传身份证反面失败");
                return result;
            }
        }

        if (handing != null && !handing.isEmpty()) {
            try {
                handing.transferTo(new File(savePath + "handing.jpg"));
                resizeImage(savePath + "handing.jpg", previewPath + "handing.jpg", 240, 240);
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
            HttpServletRequest request,
            @RequestParam String phoneNumber,
            @RequestParam(required = false) CommonsMultipartFile front,
            @RequestParam(required = false) CommonsMultipartFile back){

        Map<String, String> result = new HashMap<>();

        String savePath = "D:/PalmTouchServer/" + phoneNumber + "/debit_card/";
        File saveFolder = new File(savePath);
        if (!saveFolder.exists() && !saveFolder.isDirectory()) {
            saveFolder.mkdirs();
        }

        String previewPath = request.getSession().getServletContext().getRealPath("/resources/preview/") + phoneNumber + "/debit/";
        File previewFolder = new File(previewPath);
        if (!previewFolder.exists() && !previewFolder.isDirectory()) {
            previewFolder.mkdirs();
        }

        if (front != null && !front.isEmpty()) {
            try {
                front.transferTo(new File(savePath + "front.jpg"));
                resizeImage(savePath + "front.jpg", previewPath + "front.jpg", 240, 240);
            } catch (IOException e) {
                e.printStackTrace();
                result.put("Status", "false");
                result.put("Info", "上传结算卡正面失败");
                return result;
            }
        }
        if (back != null && !back.isEmpty()) {
            try {
                back.transferTo(new File(savePath + "back.jpg"));
                resizeImage(savePath + "back.jpg", previewPath + "back.jpg", 240, 240);
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

    public void resizeImage(String srcImgPath, String distImgPath, int width, int height) throws IOException {
        File srcFile = new File(srcImgPath);
        Image srcImg = ImageIO.read(srcFile);
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = buffImg.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

        ImageIO.write(buffImg, "jpg", new File(distImgPath));
    }
}
