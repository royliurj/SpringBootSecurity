package com.roy.security.config;

import com.roy.security.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Bean
    public CustomService customService(){
        return new CustomService();
    };

    //定制请求的授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {


       http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置登录功能
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");
        //1、/login来到登录页（自动生成的页面）
        //2、登录失败重定向到/login?error
        //3、默认post形式的/login处理登录
        //4、一旦定制了loginPage， post请求就是登录

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");
        //1、访问/logout请求，清空session
        //2、注销成功后会返回到/login?logout

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //通用用户
        auth.userDetailsService(customService());

//        auth.jdbcAuthentication().dataSource(dataSource)
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .usersByUsernameQuery("select username,password,enable from user where username=?")
//                .authoritiesByUsernameQuery("select username, rolename as authrity from role where username=?");

//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("roy").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1","VIP2","VIP3")
//                .and()
//                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2","VIP1")
//                .and()
//                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1");
    }
}
