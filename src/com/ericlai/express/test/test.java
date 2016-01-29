package com.ericlai.express.test;

import com.ericlai.express.service.LoginService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/1/25.
 */
public class test {

    @Resource
    private LoginService loginService;

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("login-servlet.xml");
        String str = loginService.getString();
        System.out.println(str);
    }
}
