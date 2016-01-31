package com.ericlai.express.controller;

import com.ericlai.express.dto.QueryDto;
import com.ericlai.express.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ERIC_LAI on 15/11/29.
 */

@Controller
public class User {

    private Logger log = LogManager.getLogger(User.class.getName());

    @Resource
    private UserServiceImpl userService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String getUser(Model model, HttpSession session) {
        log.debug("user");
//        List<String> pacIdList = userService.getPackageIdByPhone(session.getAttribute("userName").toString());
//        model.addAttribute("pacIdList", pacIdList);
        return "user";
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public void getTableInfo(HttpServletRequest request, HttpServletResponse response) {
        log.debug("user query");
        String json = "";
        if (request.getParameter("packageNo") != null ){
            //TODO:按照快递编号查询
            log.debug("query by pacId");
        }else {
            //按照寄件人或者收件人手机号码查询
            log.debug("query by phone");
            String sendPhone = request.getParameter("sendPhone");
            String receviePhone = request.getParameter("receivePhone");
            String checkMethod = request.getParameter("checkMethod");
            List<QueryDto> list;
            if (checkMethod.equals("sendPhone")) {
                log.debug("send phone: " + sendPhone);
                list = userService.getPackageInfoByPhone(sendPhone, null);
            }else {
                log.debug("receive phone: " + receviePhone);
                list = userService.getPackageInfoByPhone(null, receviePhone);
            }
            log.debug("finish query");
            json = userService.getAjaxResponse(list);
            log.debug("query result in json type: " + json);
        }
        PrintWriter out = null;
        //设置格式
        response.setContentType("application/json");
        try {
            //获取输出的格式
            out = response.getWriter();
            //输出json格式的字符串到前端
            out.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
