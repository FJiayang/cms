package com.fjy.spring.controller;

import com.fjy.spring.domain.TbFile;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.properties.ServerProperties;
import com.fjy.spring.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.fjy.spring.constant.GlobalConstant.USER_SESSION_KEY;

@Controller
public class DownLoadController {
    @Autowired
    private ServerProperties serverProperties;//服务器配置信息

    @Autowired
    private FileService fileService;

    @Resource
    HttpServletRequest request;

    /*@GetMapping("/download")
    public String toDownloadPage(){
        return "download/dodownload";
    }*/

    @GetMapping("/home/admin/download/findall")
    @ResponseBody
    public List<TbFile> toDownloadAll() {
        List<TbFile> files = fileService.findAllFile();//此处做空指针判断并抛出错误
        if (files != null)
            return files;
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }

    @GetMapping("/home/download/findone")
    @ResponseBody
    public List<TbFile> toDownloadOne() {
        TbUser user = (TbUser) request.getSession().getAttribute(USER_SESSION_KEY);
        //log.info(user.toString());
        List<TbFile> files = fileService.findByColuserid(user.getColuserid());
        //此处做空指针判断并抛出错误
        if (files != null)
            return files;
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }

    @RequestMapping("/home/download/dodownload")
    public String download(@RequestParam Integer fileId, HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;

        TbFile file = new TbFile();
        file.setColfileid(fileId);
        TbFile tbFile = fileService.findFileById(file);
        //TbFile tbFile = fileService.findFile(file);

        //System.out.println(tbFile.getColfilepath());

        String ctxPath = tbFile.getColfilepath();
        String downLoadPath = ctxPath;
        //String downLoadPath = ctxPath + tbFile.getColfilename();
        //System.out.println(downLoadPath);
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(tbFile.getColfilename().getBytes("utf-8"), "ISO8859-1"));
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

    /**
     * 传入课程名和文件夹名称，打包下载目录下所有文件
     *
     * @param courseName
     * @param folder
     * @param response
     */
    @GetMapping("/home/admin/download/downloadzip")
    public void batDownload(@RequestParam(value = "courseName") String courseName,
                            @RequestParam(value = "Folder") String folder, HttpServletResponse response) {
        //获取文件夹名称
        String paths = serverProperties.getFilePath() + "upload/" + courseName + "/" + folder;
        String zipPath = serverProperties.getFilePath() + "zip/";

        File dir = new File(zipPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<String> pathList = new ArrayList<String>();
        pathList = getFileString(paths);
        //需要压缩的文件--包括文件地址和文件名
        String[] path = (String[]) pathList.toArray(new String[0]);
        // 要生成的压缩文件地址和文件名称
        String zipFileName = courseName + folder + ".zip";
        String desPath = zipPath + "\\" + zipFileName;
        //System.out.println("打包文件存储地址："+desPath);

        File zipFile = new File(desPath);
        ZipOutputStream zipStream = null;
        FileInputStream zipSource = null;
        BufferedInputStream bufferStream = null;
        try {
            //构造最终压缩包的输出流
            zipStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < path.length; i++) {
                File file = new File(path[i]);
                //将需要压缩的文件格式化为输入流
                if (!file.isDirectory()){
                    zipSource = new FileInputStream(file);
                    //压缩条目不是具体独立的文件，而是压缩包文件列表中的列表项，称为条目，就像索引一样
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    //定位该压缩条目位置，开始写入文件到压缩包中

                    zipStream.putNextEntry(zipEntry);

                    //输入缓冲流
                    bufferStream = new BufferedInputStream(zipSource, 1024 * 10);
                    int read = 0;
                    //创建读写缓冲区
                    byte[] buf = new byte[1024 * 10];
                    while ((read = bufferStream.read(buf, 0, 1024 * 10)) != -1) {
                        zipStream.write(buf, 0, read);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != bufferStream) bufferStream.close();
                if (null != zipStream) zipStream.close();
                if (null != zipSource) zipSource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将打包好的文件输出到客户端
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;
        try {
            long fileLength = new File(desPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(zipFileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(desPath));
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
    }

    /**
     * 获取目录下所有文件的路径
     *
     * @param fileDir
     * @return
     */
    public List<String> getFileString(String fileDir) {
        List<File> fileList = new ArrayList<File>();
        File file = new File(fileDir);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        List<String> path = new ArrayList<String>();
        List<String> name = new ArrayList<String>();
        int i = 0, j = 0;
        if (files == null) {// 如果目录为空，直接退出
            path.add("空目录");
            return path;
        }
        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            } else if (f.isDirectory()) {
                //log.info(f.getAbsolutePath());
                path.add(f.getAbsolutePath());
                getFileString(f.getAbsolutePath());
            }
        }
        for (File f1 : fileList) {
            //System.out.println(f1.getName());
            path.add(f1.getAbsolutePath());
            /*name[j]=f1.getName();j++;*/
        }
        return path;
    }

   /* public static void main(String[] args) {
        List<String> path = new ArrayList<String>();
        path=getFileString("F:\\JAVA Workspace\\Temp\\upload\\信息安全\\第一次作业");
        Iterator<String> it1 = path.iterator();
        while(it1.hasNext()){
            System.out.println(it1.next());
        }
    }*/
}
