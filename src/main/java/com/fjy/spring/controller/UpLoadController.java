package com.fjy.spring.controller;

import com.fjy.spring.constant.GlobalConstant;
import com.fjy.spring.domain.TbFile;
import com.fjy.spring.domain.TbLog;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.properties.ServerProperties;
import com.fjy.spring.service.FileService;
import com.fjy.spring.service.LogService;
import com.fjy.spring.untils.FormatFileSizeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class UpLoadController {

    @Autowired
    private ServerProperties serverProperties;//服务器配置信息

    @Autowired
    private FileService fileService;//文件相关数据库操作

    @Autowired
    private LogService logService;

    @Resource
    HttpServletRequest httpServletRequest;

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

        TbUser user = (TbUser) request.getSession().getAttribute(GlobalConstant.USER_SESSION_KEY);
        //String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
        String uploadUrl = serverProperties.getFilePath() + "upload/";
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
            log.info("记录写入数据库成功");
            //System.out.println("记录写入数据库成功");
        else
            log.error("记录写入数据库失败");
        //System.out.println("记录写入数据库失败");

        log.info("文件上传到: " + uploadUrl + filename);
        log.info("文件大小: " + new FormatFileSizeUtil().GetFileSize(imageFile.getSize()));
        log.info("文件名: " + filename);

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
    public void moreUpload(HttpServletRequest request,
                           @RequestParam(value = "courseName", required = false) String courseName,
                           @RequestParam(value = "folder", required = false) String folder,
                           @RequestParam(value = "rename", required = true) boolean rename) {

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> files = multipartHttpServletRequest.getFileMap();

        TbUser user = (TbUser) request.getSession().getAttribute(GlobalConstant.USER_SESSION_KEY);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
        String dateNowStr = sdf.format(date);
        String dateNowStr2 = sdf2.format(date);
        String uploadUrl;
        //String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
        if (rename) {
            uploadUrl = serverProperties.getFilePath() + "upload/" + courseName + "/" + folder + "/";
        } else {
            uploadUrl = serverProperties.getFilePath() + "upload/";
        }


        File dir = new File(uploadUrl);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<String> fileList = new ArrayList<String>();

        for (MultipartFile file : files.values()) {
            String filename = file.getOriginalFilename();
            String suffix = "." + filename.substring(filename.lastIndexOf(".") + 1);//获取文件后缀
            TbFile tbFile = new TbFile();
            String pathname;
            if (rename) {
                pathname = uploadUrl + user.getColstudentno() + user.getColrealname() + suffix;
                tbFile.setColfilename(user.getColstudentno() + user.getColrealname() + suffix);
            } else {
                pathname = uploadUrl + filename;
                tbFile.setColfilename(filename);
            }

            File targetFile = new File(pathname);
            //若文件已存在则自动重命名
            if (targetFile.exists()){
                String bakpathname;
                if (rename) {
                    bakpathname = uploadUrl + "bak/" +user.getColstudentno() + user.getColrealname() + suffix;
                } else {
                    bakpathname = uploadUrl +"bak/"+ filename;
                }
                log.info("源文件路径:"+pathname);
                TbFile file1 = fileService.findByFilepath(pathname);
                file1.setColfilepath(bakpathname+"."+dateNowStr2+".bak");
                file1.setColfilename(file1.getColfilename()+"."+dateNowStr2+".bak");
                if (fileService.addFile(file1))
                    log.info("重命名文件数据库更新成功");
                else
                    log.error("重命名文件数据库更新失败");
                File mvfile = new File(bakpathname+"."+dateNowStr2+".bak");
                try {
                    FileUtils.moveFile(targetFile, mvfile);
                    log.info("源文件："+targetFile.getName()+"已重命名为："+ mvfile.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            log.info("文件上传到: " + uploadUrl + filename);
            log.info("文件大小: " + new FormatFileSizeUtil().GetFileSize(file.getSize()));
            log.info("文件名: " + filename);

            tbFile.setColfilesize(new FormatFileSizeUtil().GetFileSize(file.getSize()));

            tbFile.setColtime(dateNowStr);
            tbFile.setColrealname(filename);
            tbFile.setColfilepath(pathname);//文件自动学号+姓名命名
            tbFile.setColip(request.getRemoteAddr());
            tbFile.setColuserid(user.getColuserid());
            tbFile.setCourseName(courseName);
            tbFile.setWorkFolder(folder);

            TbLog logs = new TbLog();
            logs.setUserid(user.getColuserid());
            logs.setColtime(dateNowStr);
            logs.setColip(httpServletRequest.getRemoteAddr());
            logs.setColheader(user.getColname() + "上传了'" + filename + "'文件");
            logService.addLogRec(logs);

            if (fileService.addFile(tbFile))
                log.info("记录写入数据库成功");
            else
                log.error("记录写入数据库失败");

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
