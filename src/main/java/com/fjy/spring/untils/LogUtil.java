package com.fjy.spring.untils;

import com.fjy.spring.domain.TbLog;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.fjy.spring.constant.GlobalConstant.USER_SESSION_KEY;

/**
 * @author F嘉阳
 * @date 2018-04-26 12:45
 * 日志信息填充工具类
 */
@Slf4j
public class LogUtil {

    /**
     * 写入日志记录
     * @param user
     * @param content
     */
    public static TbLog addLog(TbUser user,String content,HttpServletRequest request){
        //写入日志信息
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        TbLog tbLog = new TbLog();
        tbLog.setUserid(user.getColuserid());
        tbLog.setColtime(dateNowStr);
        tbLog.setColheader(user.getColname()+" "+content);
        tbLog.setRequestURL(request.getRequestURL().toString());

        //解决nginx代理后IP地址获取问题
        tbLog.setColip(GetIPAddrUtil.getIpAddr(request));

        log.info(tbLog.toString());


        return tbLog;
    }
}
