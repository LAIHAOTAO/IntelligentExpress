package com.ericlai.express.common;

import com.ericlai.express.service.LoginServiceImpl;

import javax.servlet.http.HttpSession;

/**
 * Created by ERIC_LAI on 16/1/29.
 */

public class PublicMethod {

    private static LoginServiceImpl loginService = new LoginServiceImpl();

    /**
     * 检查密码是否正确
     * @param name 用户名
     * @param password 密码MD5
     * @return 密码正确返回真,否则假
     */

    public static boolean checkRight(String name, String password) {
        String rightPw = loginService.getPwByUserName(name);
        return password != null && password.equals(rightPw);
    }

    /**
     * 检查是否登录
     * @param session
     * @return 已经登录返回真,否则假
     */
    public static boolean checkLogin(HttpSession session) {
        String name = session.getAttribute("user").toString();
        return name != null;
    }
}
