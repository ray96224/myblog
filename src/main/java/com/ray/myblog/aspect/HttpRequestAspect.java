package com.ray.myblog.aspect;

import com.ray.myblog.dao.SystemVisitMapper;
import com.ray.myblog.entity.SystemLog;
import com.ray.myblog.service.SystemLogService;
import com.ray.myblog.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author: ray
 * @create: 2019/7/12
 */

@Aspect
@Component
public class HttpRequestAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SystemLogService systemLogService;

    @Resource
    SystemVisitMapper systemVisitMapper;

    @Pointcut("execution(public * com.ray.myblog.controller.MainController.*(..)))")
    public void sysLog(){

    }

    @Before("sysLog()")
    public void before(JoinPoint joinPoint){
        logger.info("----------开始切入----------");
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestIp = IpUtil.getIpAddr(request);
        String requestUri = request.getRequestURI();

        logger.info("requestIp="+requestIp+
                ", requestUri="+requestUri);

        SystemLog systemLog = new SystemLog();
        systemLog.setIp(requestIp);
        systemLog.setUri(requestUri);
        systemLogService.insertSysLog(systemLog);

        HttpSession session = request.getSession();
        if (session.getAttribute("visitor") == null){
            systemVisitMapper.increaseTimes();
            session.setAttribute("visitor", UUID.randomUUID().toString());
        }

    }

    @After("sysLog()")
    public void after(){
        logger.info("----------执行完成----------");
    }

    @AfterReturning(pointcut = "sysLog()", returning = "object")
    public void getAfterReturn(Object object) {
        logger.info("切入返回："+object);
    }



}
