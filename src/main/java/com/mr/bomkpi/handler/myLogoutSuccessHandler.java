package com.mr.bomkpi.handler;

import com.mr.bomkpi.config.MySecurityConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myLogoutSuccessHandler")
public class myLogoutSuccessHandler implements LogoutSuccessHandler {
    private final Log logger = LogFactory.getLog(MySecurityConfig.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");
        response.sendRedirect("/");
    }
}
