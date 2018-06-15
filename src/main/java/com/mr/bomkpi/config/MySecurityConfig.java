package com.mr.bomkpi.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Administrator
 */
@EnableWebSecurity
public class MySecurityConfig  extends WebSecurityConfigurerAdapter{
    private final Log logger = LogFactory.getLog(MySecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("Using custom configure(HttpSecurity).");

        http
                .authorizeRequests()
                .antMatchers("/bootstrap/**","/dist/**","/js/**","/plugins/**","/images/**","/","/login").permitAll()
                .antMatchers("/user/**","/login").hasRole("admin")
                .antMatchers("/counter/**").hasRole("kuguan")
                .antMatchers("/order/**").hasRole("kuguan")
                .antMatchers("/task/**").hasRole("kuguan")
                .antMatchers("/singleTask/**","/teamTask/**").hasRole("caozuogong")
                .antMatchers("/check/**").hasRole("check")
                .antMatchers("/kpi/**").hasRole("check")
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("admin");
    }
}
