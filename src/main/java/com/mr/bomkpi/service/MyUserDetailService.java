package com.mr.bomkpi.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//E:1524710989265:dd76ff0602e1285b271c7f05a6b13e8e admin
        com.mr.bomkpi.entity.User u = userService.findByUsername(username);
        logger.info("登录用户名-----" + username + ",数据库密码----" + u.getPassword() + "，数据库权限----" + AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new User(username,u.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }


}
