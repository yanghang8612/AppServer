package com.huachuang.server.controller;

import com.huachuang.server.service.PictureRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

/**
 * Created by Asuka on 2017/3/31.
 */

@Controller
@RequestMapping("/Picture")
public class PictureRequestController {

    @Resource
    private PictureRequestService pictureRequestService;

    @ResponseBody
    @RequestMapping(value = "/UploadHeader", method = RequestMethod.POST)
    public boolean EditHPic(
            HttpServletRequest request,
            @RequestParam String userID,
            @RequestParam("header") CommonsMultipartFile header){

        String path = request.getSession().getServletContext().getRealPath("/resources/header/");
        if (!header.isEmpty()) {
            try {
                header.transferTo(new File(path + userID + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/DownloadHeader", method = RequestMethod.POST)
    public void DownloadHPic(
            HttpServletResponse response,
            @RequestParam String userID){

    }
}
