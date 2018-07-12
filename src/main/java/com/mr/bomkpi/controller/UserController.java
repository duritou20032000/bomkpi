package com.mr.bomkpi.controller;

import com.mr.bomkpi.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
//    private RequestCache requestCache = new HttpSessionRequestCache();
//    private RedirectStrategy redirectStrategy;
//
//    @RequestMapping("/authentication/require")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public SimpleResponse arequireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//        if (savedRequest != null) {
//            String targetUrl = savedRequest.getRedirectUrl();
//            logger.info("引发跳转的请求是：" + targetUrl);
//            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
//                redirectStrategy.sendRedirect(request, response, "/");
//            }
//        }
//        return new SimpleResponse("访问的服务需要身份验证，请到登录页进行登录！");
//    }


}
