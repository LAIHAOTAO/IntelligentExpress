package com.ericlai.express.controller;

import com.ericlai.express.common.Constant;
import com.ericlai.express.common.PublicMethod;
import com.ericlai.express.dto.Person;
import com.ericlai.express.service.SysManageServiceImpl;
import com.ericlai.express.util.IdBuilder;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ERIC_LAI on 15/11/27.
 */

@Controller
public class SysManager {

    private Logger log = LogManager.getLogger(SysManager.class.getName());

    @Resource
    private SysManageServiceImpl sysManageService;

    @RequestMapping(value = "sysManager", method = RequestMethod.GET)
    public String sysManager() {
        return "sysManager";
    }

    @RequestMapping(value = "managePostman", method = RequestMethod.GET)
    public void getPostman(HttpServletResponse response) {
        log.debug("get all postman");
        List<Person> list = sysManageService.getPostman();
        String json = PublicMethod.getAjaxResponse(list, "postman");
        log.debug("postman result in json type: " + json);
        PublicMethod.SendJsonToFront(response, json);
    }

    @RequestMapping(value = "addPostman", method = RequestMethod.POST)
    public void addPostman(HttpServletRequest request, HttpServletResponse response) {
        log.debug("add Postman begin");
        Map<String, String> mainMap = new HashMap<>();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String logNm = request.getParameter("logNm");
        String logPw = request.getParameter("logPw");
        logPw = MD5Util.getMD5String(logPw);
        String gender = request.getParameter("gender");
        log.debug("name: " + name);
        Person record = new Person();
        record.setPersonId(IdBuilder.getPersonId());
        record.setIdCrl(Constant.POSTMAN);
        record.setName(name);
        record.setPhone(phone);
        record.setLogNm(logNm);
        record.setLogPw(logPw);
        record.setGender(gender);
        record.setDeleteFlag(Constant.DO_NOT_DELETE);
        try {
            sysManageService.addPoster(record);
            mainMap.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            mainMap.put("result", "fail");
        }
        String json = JsonBuildUtil.packToObject(mainMap, null, null);
        PublicMethod.SendJsonToFront(response, json);
    }

    @RequestMapping(value = "deletePostman", method = RequestMethod.GET)
    public void deletePostman(HttpServletRequest request, HttpServletResponse response) {
        String personId = request.getParameter("personId");
        log.debug("postman delete: " + personId);
        Map<String, String> mainMap = new HashMap<>();
        try {
            sysManageService.deletePostman(personId);
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