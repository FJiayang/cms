package com.fjy.spring.controller;

import com.fjy.spring.domain.TbFeedBack;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.properties.ServerProperties;
import com.fjy.spring.service.FeedBackService;
import com.fjy.spring.untils.RedirectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.fjy.spring.constant.GlobalConstant.USER_SESSION_KEY;

@RestController
@Slf4j
public class FeedBackController {

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private ServerProperties serverProperties;

    @Resource
    HttpServletRequest request;

    @PostMapping("/home/dofeedback")
    public boolean doFeedBack(@RequestParam(value = "content") String content){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        TbFeedBack feedBack = new TbFeedBack();
        feedBack.setContent(content);
        feedBack.setTime(dateNowStr);
        TbUser user = (TbUser)request.getSession().getAttribute(USER_SESSION_KEY);
        feedBack.setUserid(user.getColuserid());
        RedirectUtil red = new RedirectUtil();
        if (feedBackService.addContent(feedBack)){
            log.info("反馈信息写入数据库成功");
            return true;
        }
        return false;

    }
}
