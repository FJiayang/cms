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

import java.io.File;

@Controller
public class DeleteController {
    @Autowired
    private FileService fileService;//文件相关数据库操作

    @GetMapping("/home/findfile")
    @ResponseBody
    public String findFilePath(@RequestParam(value = "fileid") Integer fileid){
        TbFile tbFile = new TbFile();
        tbFile.setColfileid(fileid);
        TbFile resfile = fileService.findFileById(tbFile);
        return  resfile.getColfilepath();
    }
    /**
     * 删除文件，可以是文件或文件夹
     */
    @RequestMapping("/home/filedelete")
    @ResponseBody
    public void delete(@RequestParam(value = "fileid") Integer fileid)throws Exception {
        TbFile tbFile = new TbFile();
        tbFile.setColfileid(fileid);
        TbFile resfile = fileService.findFileById(tbFile);
        File filepath = new File(resfile.getColfilepath());
        if (!filepath.exists()) {
            System.out.println("删除文件失败:" + resfile.getColfilename() + "不存在！");
        } else {
            if (filepath.isFile()){
                deleteFile(resfile.getColfilepath(),resfile.getColfileid());
                new UserException(ResultEnum.SUCCESS);
            }
            else
                deleteDirectory(resfile.getColfilepath());
        }

    }

    /**
     * 删除单个文件
     */
    public boolean deleteFile(String fileName,Integer fileid) {
        File file = new File(fileName);
        TbFile tbFile = new TbFile();
        tbFile.setColfileid(fileid);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                fileService.deleteFileById(tbFile);
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *功能暂时不开放
     * @param path
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String path) {
        /*// 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                TbFile tbFile = new TbFile();
                tbFile.setColfilename(files[i].getName());
                fileService.deleteFileById(tbFile);
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }*/

        /*File dir=new File(path);
        if(dir.exists()){
            File[] tmp=dir.listFiles();
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].isDirectory()){
                    deleteDirectory(path+"/"+tmp[i].getName());
                }
                 else{
                    tmp[i].delete();
                }
            }
            dir.delete();
        }*/
        return true;
    }
}
