package com.ericlai.express.controller;

import com.ericlai.express.common.PublicMethod;
import com.ericlai.express.dto.Person;
import com.ericlai.express.dto.QueryDto;
import com.ericlai.express.service.LoginServiceImpl;
import com.ericlai.express.service.UserServiceImpl;
import com.ericlai.express.util.GetBeanMap;
import com.ericlai.express.util.JsonBuildUtil;
import com.ericlai.express.util.MD5Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户操作页面模块控制器代码
 * Created by ERIC_LAI on 15/11/29.
 */

@Controller
public class User {

    private Logger log = LogManager.getLogger(User.class.getName());

    @Resource
    private UserServiceImpl userService;

    @Resource
    private LoginServiceImpl loginService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public void getUser(HttpSession session, HttpServletResponse response) {
        log.debug("user");
        List<QueryDto> pacIdList = userService.getPackageIdByPhone(session.getAttribute("user").toString());
        Map<String, String> mainMap = new HashMap<>();
        int i = 0;
        for (QueryDto aList : pacIdList) {
            mainMap.put("pacId" + i, aList.getPacId());
            i++;
        }
        String json = JsonBuildUtil.packToObject(mainMap, null, null);
        log.debug(json);
        PublicMethod.SendJsonToFront(response, json);
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public void getTableInfo(HttpServletRequest request, HttpServletResponse response) {
        log.debug("user query");
        String json = "";
        String checkMethod = request.getParameter("checkMethod");
        log.debug("checkMethod: " + checkMethod);
        List<QueryDto> list;
        if (checkMethod.equals("packageId")) {
            log.debug("query by pacId");
            String packageNo = request.getParameter("packageNo");
            log.debug(packageNo);
            list = userService.getPackageInfoByPacId(packageNo);
        } else {
            //按照寄件人或者收件人手机号码查询
            log.debug("query by phone");
            String sendPhone = request.getParameter("sendPhone");
            String receviePhone = request.getParameter("receivePhone");
            if (checkMethod.equals("sendPhone")) {
                log.debug("send phone: " + sendPhone);
                list = userService.getPackageInfoByPhone(sendPhone, null);
            } else {
                log.debug("receive phone: " + receviePhone);
                list = userService.getPackageInfoByPhone(null, receviePhone);
            }
            log.debug("finish query");
        }
        json = userService.getAjaxResponse(list);
        log.debug("query result in json type: " + json);
        PublicMethod.SendJsonToFront(response, json);
    }

    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public void getPersonInfo(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        log.debug("modify get");
        Map<String, String> mainMap;
        String userName = session.getAttribute("user").toString();
        Person user = loginService.getPersonByUserName(userName);
        log.debug("query finish");
        mainMap = GetBeanMap.getBeanFieldAndValue(user);
        String json = JsonBuildUtil.packToObject(mainMap, null, null);
        log.debug(json);
        PublicMethod.SendJsonToFront(response, json);
    }

    @RequestMapping(value = "modifyInfo", method = RequestMethod.POST)
    public void updatePersonInfo(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person();
        String personId = request.getParameter("personId");
        String personNm = request.getParameter("personNm");
        String phone = request.getParameter("phone");
        String logNm = request.getParameter("logNm");
        person.setPersonId(personId);
        person.setName(personNm);
        person.setPhone(phone);
        person.setLogNm(logNm);
        Map<String, String> mainMap = new HashMap<>();
        try {
            userService.updateByPrimaryKeySelective(person);
            mainMap.put("result", "success");
        }catch (Exception e){
            e.printStackTrace();
            mainMap.put("result", "fail");
        }finally {
            String json = JsonBuildUtil.packToObject(mainMap, null, null);
            PublicMethod.SendJsonToFront(response, json);
        }
    }

    @RequestMapping(value = "modifyPw", method = RequestMethod.POST)
    public void modifyPw(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String oldPw = MD5Util.getMD5String(request.getParameter("oldPw"));
        String oldRight = MD5Util.getMD5String(loginService.getPwByUserName(session.getAttribute("user").toString()));
        Map<String, String> mainMap = new HashMap<>();
        if ( !oldPw.equals(oldRight)) {
            log.debug("oldPw is incorrect");
            mainMap.put("result", "oldFalse");
        }else {
            log.debug("oldPw is correct, begin to modify password");
            String newPw = MD5Util.getMD5String(request.getParameter("newPw"));
            log.debug("old password: " + oldPw);
            log.debug("new password: " + newPw);
            try {
                userService.updataLogPw(oldPw, newPw);
                log.debug("successful update");
                mainMap.put("result", "sccess");
            }catch (Exception e) {
                e.printStackTrace();
                mainMap.put("result", "fail");
            }
        }
        String json = JsonBuildUtil.packToObject(mainMap, null, null);
        PublicMethod.SendJsonToFront(response, json);
    }
}
