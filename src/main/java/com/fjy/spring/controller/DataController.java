package com.fjy.spring.controller;

import com.fjy.spring.domain.*;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.service.*;
import com.fjy.spring.untils.CodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.fjy.spring.constant.GlobalConstant.USER_SESSION_KEY;

@RestController
public class DataController {

    @Autowired
    private WorkDetailService workDetailService;

    @Autowired
    private LogService logService;

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private VUserfileService vUserfileService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private AdminService adminService;

    @Resource
    private HttpServletRequest httpServletRequest;

    @GetMapping("/home/findAllHomework")
    public List<VWorkDetail> findAllHomework() {
        Format f = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        // 今天-1天
        c.add(Calendar.DAY_OF_MONTH, -1);

        Date tomorrow = c.getTime();

        List<VWorkDetail> homeworks = workDetailService.findAllVWorkDetailAfterTime(f.format(tomorrow));
        if (homeworks != null) {
            return homeworks;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/findvlog")
    public List<VLog> findlog() {
        List<VLog> vlogs = logService.findvlog();
        if (vlogs != null) {
            return vlogs;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/admin/findvfeedback")
    public List<VFeedBack> findAllVFeedback() {
        List<VFeedBack> feedBacks = feedBackService.findAllVFeedback();
        if (feedBacks != null) {
            return feedBacks;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/findvcourse")
    public List<VCourse> findVCourse() {
        List<VCourse> vCourses = courseService.findAllVCourse();
        if (vCourses != null) {
            return vCourses;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/admin/findalluser")
    public List<TbUser> findAllUser() {
        List<TbUser> users = userService.findAllUser();
        if (users != null) {
            return users;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/findallvhomework")
    public List<VHomework> findAllVHomework() {
        List<VHomework> vHomeworks = homeworkService.findAllVHomework();
        if (vHomeworks != null) {
            return vHomeworks;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/admin/findStudentInCourseFile")
    public List<TbStudentlist> findStudentInCourseFile(
            @RequestParam(value = "Folder") String Folder, @RequestParam(value = "CourseName") String CourseName) {
        List<TbStudentlist> files = vUserfileService.findStudentNoByWorkFolderAndCourseName(Folder, CourseName);
        if (files != null) {
            return files;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/userinfo")
    public VUserinfo findUserInfo() {
        TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(USER_SESSION_KEY);
        return userService.findUserInfo(user.getColuserid());
    }

    /**
     * 存储密保问题
     *
     * @param userque
     * @return
     */
    @PostMapping("/home/adduserque")
    public boolean adduserque(TbUserque userque) throws Exception {
        //对密保问题加密存储
        userque.setAnswer(new BigInteger(CodingUtil.encryptSHA(userque.getAnswer().getBytes())).toString(32));
        return userService.addUserQue(userque);
    }

    /**
     * 判断密保问题是否正确，正确返回true，错误返回false，其余反馈异常对象
     *
     * @param name
     * @param question
     * @param answer
     * @return
     * @throws Exception
     */
    @GetMapping("/finduserque")
    public boolean findUserQue(@RequestParam(value = "name") String name
            , @RequestParam(value = "question") String question
            , @RequestParam(value = "answer") String answer) throws Exception {
        Optional<VUserque> userque = userService.findUserQueByName(name);
        if (!userque.isPresent()) {
            throw new UserException(ResultEnum.EMPTY_QUESTION);
        } else if (question.equals(userque.get().getQuestion())) {
            if (new BigInteger(CodingUtil.encryptSHA(answer.getBytes())).toString(32).equals(userque.get().getAnswer())) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new UserException(ResultEnum.QUESTION_ERROR);
        }
    }

    @PostMapping("/resetPass")
    public boolean resetPass(@RequestParam(value = "name") String name
            , @RequestParam(value = "password") String password,
                             @RequestParam(value = "question") String question
            , @RequestParam(value = "answer") String answer) throws Exception {
        //log.info("name:{}, password:{}, question:{}, answer:{}",name,password,question,answer);
        if (findUserQue(name, question, answer)) {
            //service方法内含有对密码加密的操作
            return userService.updateColpasswordByColname(password, name);
        } else {
            throw new UserException(ResultEnum.ILLEGAL_ACCESS);
        }
    }

    @GetMapping("/home/findAllNotice")
    public List<TbNotice> findAllNotice() {
        return noticeService.findAll();
    }

    @PostMapping("/home/admin/addNotice")
    public boolean addNotice(String content) {
        TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(USER_SESSION_KEY);
        TbNotice notice = new TbNotice();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        Optional<VAdmin> vAdmin = adminService.findAdminByUserId(user.getColuserid());
        if (vAdmin.isPresent()) {
            VAdmin admin = vAdmin.get();
            notice.setAdminid(admin.getAdminid());
            notice.setIssueTime(dateNowStr);
            notice.setNoticeContent(content);
            return noticeService.addOne(notice) != null;
        } else {
            return false;
        }
    }

    @PostMapping("/home/admin/addoneversion")
    public boolean addOneVersion(TbVersion version) {
        return versionService.addOneVersion(version) != null;
    }

    @PostMapping("/home/admin/addversion")
    public boolean addVersion(@RequestBody List<TbVersion> versions) {
        return versionService.addAllVersion(versions) != null;
    }

    @GetMapping("/home/findallversion")
    public List<TbVersion> findAllVersion() {
        return versionService.findAll();
    }
}
