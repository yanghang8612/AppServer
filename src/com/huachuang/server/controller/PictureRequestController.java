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
import java.io.File;
import java.io.IOException;

/**
 * Created by Asuka on 2017/3/31.
 */

@Controller
@RequestMapping("/Picture")
public class PictureRequestController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private PictureRequestService pictureRequestService;

    @ResponseBody
    @RequestMapping(value = "/UploadHeader", method = RequestMethod.POST)
    public boolean EditHPic(@RequestParam String userID, @RequestParam("header") CommonsMultipartFile header){
        String path = request.getSession().getServletContext().getRealPath("/resources/header/");
        if (!header.isEmpty()) {
            try {
                header.transferTo(new File(path + userID));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

//    /*
//        下载头像
//     */
//    @RequestMapping(value = "/DownloadHeader", method = RequestMethod.GET)
//    public void DownloadHPic(ModelMap modelMap, @RequestParam String phoneNum, HttpServletResponse response){
//        UserManageService userManageService = new UserManageService();
//        BufferedInputStream bufferedInputStream = userManageService.DownloadHPic(phoneNum);
//        if (bufferedInputStream != null){
//            byte[] buffer = new byte[1024];
//            try {
//                OutputStream outputStream = response.getOutputStream();
//                int i = bufferedInputStream.read(buffer);
//                while (i != -1){
//                    outputStream.write(buffer, 0, i);
//                    i = bufferedInputStream.read(buffer);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (bufferedInputStream != null){
//                    try {
//                        bufferedInputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
}
