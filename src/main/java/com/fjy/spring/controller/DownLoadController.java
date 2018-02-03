package com.fjy.spring.controller;

import com.fjy.spring.domain.TbFile;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class DownLoadController {
    @Autowired
    private FileService fileService;

    @GetMapping("/download")
    public String toDownloadPage(){
        return "download";
    }

    @GetMapping("/download/findall")
    @ResponseBody
    public List<TbFile> toDownloadAll(){
        List<TbFile> files = fileService.findAllFile();//此处做空指针判断并抛出错误
        if (files!=null)
            return files;
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }

    @RequestMapping("/download/dodownload")
    public String download(@RequestParam String fileName , HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;

        TbFile file = new TbFile();
        file.setColfilename(fileName);

        TbFile tbFile = fileService.findFile(file);

        System.out.println(tbFile.getColfilepath());

        String ctxPath = tbFile.getColfilepath();
        String downLoadPath = ctxPath + fileName;
        System.out.println(downLoadPath);
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return null;

    }
}
