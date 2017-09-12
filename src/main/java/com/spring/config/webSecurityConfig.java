package com.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by K on 2017/9/12.
 */
@Configuration
@EnableWebSecurity
public class webSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。
                    .antMatchers("/","/login").permitAll()//访问：/,/login 无需登录认证权限
                    .anyRequest().authenticated()//其他所有资源都需要认证，登陆后访问
                    .antMatchers("/hello").hasAuthority("ADMIN")//登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
                    .and()
                .formLogin()//通过formLogin()定义当需要用户登录时候，转到的登录页面。
                    .loginPage("/login")//指定登录页面访问的路径是/login
                    .defaultSuccessUrl("/chat")//登录成功后转向/chat路径
                    .permitAll()
                    .and()
                .logout()
//                    .logoutSuccessUrl("/exit")//退出后转向/exit路径
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("wy").password("wy").roles("ADMIN")
                .and()
                .withUser("lll").password("lll").roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }
}
