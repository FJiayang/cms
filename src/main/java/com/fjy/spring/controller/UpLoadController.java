package com.fjy.spring.controller;

import com.fjy.spring.constant.GlobalConstant;
import com.fjy.spring.domain.TbFile;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.properties.ServerProperties;
import com.fjy.spring.service.FileService;
import com.fjy.spring.untils.FormatFileSizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class UpLoadController {

    @Autowired
    private ServerProperties serverProperties;//服务器配置信息

    @Autowired
    private FileService fileService;//文件相关数据库操作

    @GetMapping("/toOneUpload")
    public String toOneUpload() {
        return "oneUpload";
    }

    @GetMapping("/toMoreUpload")
    public String toMoreUpload() {
        return "moreUpload";
    }

    /**
     * 测试文件上传路径地址
     *
     * @param imageFile
     * @param request
     * @return
     */
    @RequestMapping("/test")
    @ResponseBody
    public String testURL(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) {
        String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
        return uploadUrl;
    }

    /**
     * 单文件上传
     *
     * @param imageFile
     * @param request
     * @return
     */
    @RequestMapping(value = "/oneUpload")
    public String oneUpload(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) {

        TbUser user = (TbUser)request.getSession().getAttribute(GlobalConstant.USER_SESSION_KEY);
        //String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String uploadUrl =  serverProperties.getFilePath()+ "upload/";
        String filename = imageFile.getOriginalFilename();
        File dir = new File(uploadUrl);
        if (!dir.exists()) {//判断目录是否存在，否则自动创建
            dir.mkdirs();
        }
        /**
         * 存储文件信息
         */
        TbFile file = new TbFile();
        file.setColfilesize(new FormatFileSizeUtil().GetFileSize(imageFile.getSize()));
        file.setColfilename(filename);
        file.setColfilepath(uploadUrl + filename);
        file.setColip(request.getRemoteAddr());
        file.setColuserid(user.getColuserid());
        if (fileService.addFile(file))
            System.out.println("记录写入数据库成功");
        else
            System.out.println("记录写入数据库失败");

        System.out.println("文件上传到: " + uploadUrl + filename);
        System.out.println("文件大小: " + new FormatFileSizeUtil().GetFileSize(imageFile.getSize()));
        System.out.println("文件名: " + filename);

        File targetFile = new File(uploadUrl + filename);
        if (!targetFile.exists()) {
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try {
            imageFile.transferTo(targetFile);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:" + request.getScheme() + "://" + request.getServerName() + ":"
                + serverProperties.getPortNum() + request.getContextPath() + "/upload/" + filename;
        //return "redirect:http://localhost:8080/cms/upload/" + filename;
        // return "index";
    }

    /**
     * 多文件上传
     *
     * @param request
     * @return
     */
    @RequestMapping("/moreUpload")
    public void moreUpload(HttpServletRequest request) {

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();

        TbUser user = (TbUser)request.getSession().getAttribute(GlobalConstant.USER_SESSION_KEY);
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        //String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String uploadUrl = serverProperties.getFilePath()+ "upload/";

        File dir = new File(uploadUrl);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<String> fileList = new ArrayList<String>();

        for (MultipartFile file : files.values()) {
            String filename = file.getOriginalFilename();
            File targetFile = new File(uploadUrl + filename);

            System.out.println("文件上传到: " + uploadUrl + filename);
            System.out.println("文件大小: " + new FormatFileSizeUtil().GetFileSize(file.getSize()));
            System.out.println("文件名: " + filename);

            TbFile tbFile = new TbFile();
            tbFile.setColfilesize(new FormatFileSizeUtil().GetFileSize(file.getSize()));
            tbFile.setColfilename(filename);
            tbFile.setColtime(currentTime);
            tbFile.setColfilepath(uploadUrl + filename);
            tbFile.setColip(request.getRemoteAddr());
            tbFile.setColuserid(user.getColuserid());

            if (fileService.addFile(tbFile))
                System.out.println("记录写入数据库成功");
            else
                System.out.println("记录写入数据库失败");

            if (!targetFile.exists()) {
                try {
                    targetFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    file.transferTo(targetFile);
                    fileList.add(
                            request.getScheme() + "://" + request.getServerName() + ":"
                                    + serverProperties.getPortNum() + request.getContextPath() + "/upload/"
                                    + file.getOriginalFilename());
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
