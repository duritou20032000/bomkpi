package com.mr.bomkpi.config;

import com.mr.bomkpi.service.MyUserDetailService;
import com.mr.bomkpi.util.PasswordUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author Administrator
 */
@EnableWebSecurity
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    private final Log logger = LogFactory.getLog(MySecurityConfig.class);

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private AuthenticationSuccessHandler MyAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler MyAuthenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/user/login").permitAll()
                .successHandler(MyAuthenticationSuccessHandler)
                .failureHandler(MyAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/","/login").permitAll()
                .antMatchers("/bootstrap/**", "/dist/**", "/js/**", "/plugins/**", "/images/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .logout()
                .logoutUrl("/user/logout")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(myLogoutSuccessHandler);

//        http.formLogin().loginPage("/login")
//                .usernameParameter("username").passwordParameter("password").loginProcessingUrl("/user/login").permitAll()
//                .and()
//                .authorizeRequests()
//                //要求授权 这种.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")  要两个条件都具备才行
//                .antMatchers("/bootstrap/**", "/dist/**", "/js/**", "/plugins/**", "/images/**", "/", "/login").permitAll()
//                .antMatchers("/**").hasAnyAuthority("SystemAdminGroup", "CommonGroup", "SuperUserGroup")
//                .antMatchers("/counter/**").hasAnyAuthority("SystemAdminGroup")
//                .antMatchers("/order/**").hasAnyRole("kuguan", "admin")
//                .antMatchers("/task/**").hasAnyRole("kuguan", "admin")
//                .antMatchers("/singleTask/**", "/teamTask/**").hasAnyRole("caozuogong", "admin")
//                .antMatchers("/check/**").hasAnyRole("check", "admin")
//                .antMatchers("/kpi/**").hasAnyRole("check", "admin")
//                //其余的只要求身份认证
//                .anyRequest()
//                .authenticated()
//                .and()
//                .logout().logoutUrl("/user/logout").permitAll()
//                .and()
//                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //内存明文密码
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("admin");
//       数据库保存的密码，这里注意要加密passwordEncoder，很多视频没讲这里
        /**
         * 如果是从WMS跳转过来的，flag=1，则不适用加密的方式
         */
        auth.userDetailsService(myUserDetailService).passwordEncoder(new PasswordUtil());

    }


}
