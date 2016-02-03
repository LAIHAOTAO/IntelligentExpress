package com.ericlai.express.controller;

import com.ericlai.express.common.PublicMethod;
import com.ericlai.express.dto.Address;
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
import java.util.Objects;

/**
 * 用户操作页面模块控制器代码
 * Created by ERIC_LAI on 15/11/29.
 */

@Controller
public class User {

    /**
     * 获取日志实例
     */
    private Logger log = LogManager.getLogger(User.class.getName());

    /**
     * 注入userService
     */
    @Resource
    private UserServiceImpl userService;

    /**
     * 请求用户页面
     * @return user.jsp
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String getUser() {
        return "user";
    }

    /**
     * Ajax请求某用户对应的全部快件ID
     * @param session
     * @param response
     */
    @RequestMapping(value = "pacId", method = RequestMethod.GET)
    public void getUserPacId(HttpSession session, HttpServletResponse response) {
        List<QueryDto> pacIdList = userService.getPackageIdByUserName(session.getAttribute("user").toString());
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

    /**
     * Ajax请求用户对应的快件信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public void getTableInfo(HttpServletRequest request, HttpServletResponse response) {
        log.debug("user query");
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
        String json = userService.getAjaxResponse(list, "queryDto");
        log.debug("query result in json type: " + json);
        PublicMethod.SendJsonToFront(response, json);
    }

    /**
     * Ajax请求用户的个人信息
     * @param session
     * @param response
     */
    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public void getPersonInfo(HttpSession session, HttpServletResponse response) {
        log.debug("modify get");
        Map<String, String> mainMap;
        String userName = session.getAttribute("user").toString();
        Person user = userService.getPersonByUserName(userName);
        log.debug("query finish");
        mainMap = GetBeanMap.getBeanFieldAndValue(user);
        String json = JsonBuildUtil.packToObject(mainMap, null, null);
        log.debug(json);
        PublicMethod.SendJsonToFront(response, json);
    }

    /**
     * Ajax更新用户个人资料
     * @param request
     * @param response
     */
    @RequestMapping(value = "modifyInfo", method = RequestMethod.POST)
    public void updatePersonInfo(HttpServletRequest request, HttpServletResponse response) {
        log.debug("modifyInfo begin");
        Person record = new Person();
        String personId = request.getParameter("personId");
        String personNm = request.getParameter("name");
        String phone = request.getParameter("phone");
        String logNm = request.getParameter("logNm");
        String gender = request.getParameter("gender");
        log.debug("modifyInfo: " + personId + personNm + phone + logNm + gender);
        record.setPersonId(personId);
        record.setName(personNm);
        record.setPhone(phone);
        record.setLogNm(logNm);
        record.setGender(gender);
        Map<String, String> mainMap = new HashMap<>();
        try {
            int num = userService.updateByPrimaryKeySelective(record);
            log.debug("modify record number: " + num);
            mainMap.put("result", "success");
        }catch (Exception e){
            e.printStackTrace();
            mainMap.put("result", "fail");
        }finally {
            String json = JsonBuildUtil.packToObject(mainMap, null, null);
            PublicMethod.SendJsonToFront(response, json);
        }
    }

    /**
     * Ajax更改用户密码
     * @param request
     * @param response
     * @param session
     */
    @RequestMapping(value = "modifyPw", method = RequestMethod.POST)
    public void modifyPw(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String personId = request.getParameter("personId");
        String oldPw = MD5Util.getMD5String(request.getParameter("oldPw"));
        String oldRight = userService.getPwByUserName(session.getAttribute("user").toString());
        log.debug("old password: " + oldPw);
        log.debug("old correct password: " + oldRight);
        Map<String, String> mainMap = new HashMap<>();
        if (!oldPw.equals(oldRight)) {
            log.debug("oldPw is incorrect");
            mainMap.put("result", "oldFalse");
        } else {
            log.debug("oldPw is correct, begin to modify password");
            String newPw = MD5Util.getMD5String(request.getParameter("newPw"));
            Person record = new Person();
            record.setPersonId(personId);
            record.setLogPw(newPw);
            log.debug("old password: " + oldPw);
            log.debug("new password: " + newPw);
            try {
                log.debug("personId: " + personId);
                userService.updateByPrimaryKeySelective(record);
                log.debug("successful update");
                mainMap.put("result", "success");
            } catch (Exception e) {
                e.printStackTrace();
                mainMap.put("result", "fail");
            }
        }
        String json = JsonBuildUtil.packToObject(mainMap, null, null);
        PublicMethod.SendJsonToFront(response, json);
    }

    /**
     * Ajax获取用户对应的地址信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "address", method = RequestMethod.GET)
    public void getAddress(HttpServletRequest request, HttpServletResponse response) {
        log.debug("get address by personId");
        String personId = request.getParameter("personId");
        log.debug("personId: " + personId);
        List<Address> list = userService.getAddress(personId);
        String json = userService.getAjaxResponse(list, "address");
        log.debug("address result in json type: " + json);
        PublicMethod.SendJsonToFront(response, json);
    }


    @RequestMapping(value = "addrDelete", method = RequestMethod.POST)
    public void addrDelete(HttpServletRequest request, HttpServletResponse response) {
        String addrId = request.getParameter("addrId");
        log.debug("address delete: " + addrId);
        Map<String, String> mainMap = new HashMap<>();
        try {
            userService.addrDeleteByPrimaryKey(Integer.parseInt(addrId));
            mainMap.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            mainMap.put("result", "fail");
        }
        String json = JsonBuildUtil.packToObject(mainMap, null, null);
        log.debug(json);
        PublicMethod.SendJsonToFront(response, json);
    }
}
