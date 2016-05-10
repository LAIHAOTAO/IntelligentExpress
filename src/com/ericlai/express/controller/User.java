package com.ericlai.express.controller;

import com.ericlai.express.common.PublicMethod;
import com.ericlai.express.dao.AddressMapper;
import com.ericlai.express.dao.DynamicqrMapper;
import com.ericlai.express.dao.PackageMapper;
import com.ericlai.express.dao.PersonMapper;
import com.ericlai.express.dto.*;
import com.ericlai.express.dto.Package;
import com.ericlai.express.service.UserServiceImpl;
import com.ericlai.express.util.GetBeanMap;
import com.ericlai.express.util.IdBuilder;
import com.ericlai.express.util.JsonBuildUtil;
import com.ericlai.express.util.MD5Util;
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private PersonMapper personMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private PackageMapper packageMapper;

    @Resource
    private DynamicqrMapper dynamicqrMapper;

    // 付款状态：0未付款 1已付款
    private int payStatus = 0;
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
        String json = PublicMethod.getAjaxResponse(list, "queryDto");
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
     * 取得一个人的相关信息送回手机端
     * @param request
     * @param response
     */
    @RequestMapping(value = "personMobile", method = RequestMethod.GET)
    public void personMobile(HttpServletRequest request, HttpServletResponse response) {
        log.debug("modify get");
        Map<String, String> mainMap;
        String userName = request.getParameter("userName");
        Person user = userService.getPersonByUserName(userName);
        log.debug("query finish");
        mainMap = GetBeanMap.getBeanFieldAndValue(user);

        String receviePhone = user.getPhone();
        List<QueryDto> pacList = userService.getPackageInfoByPhone(null, receviePhone);
        List<Address> addrList = userService.getAddress(user.getPersonId());
        ArrayList<String> subKey = new ArrayList<>();
        ArrayList<Map<String, String>> subValue = new ArrayList<>();
        HashMap<String, String> pacIdMap = new HashMap<>();
        HashMap<String, String> addrMap = new HashMap<>();
        subKey.add("otherInfo");
        int count = 0;
        for (QueryDto queryDto : pacList) {
            pacIdMap.put("pacId" + count, queryDto.getPacId());
            count++;
        }
        subValue.add(pacIdMap);
        count = 0;
        for (Address addr : addrList) {
            addrMap.put("addr" + count, addr.getAddrInfo());
            count++;
        }
        subValue.add(addrMap);
        String json = JsonBuildUtil.packToObject(mainMap, subKey, subValue);
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
        String json = PublicMethod.getAjaxResponse(list, "address");
        log.debug("address result in json type: " + json);
        PublicMethod.SendJsonToFront(response, json);
    }

    /**
     * Ajax删除某用户的已有地址信息
     * @param request
     * @param response
     */
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

    /**
     * 增加包裹
     * 逻辑：检查是否有这个收件人，没有则建立此人。检查地址是否存在，没有则建立新的地址。
     * @param request
     * @param response
     */
    @RequestMapping(value = "addPackageMobile", method = RequestMethod.POST)
    public void addPackage(HttpServletRequest request, HttpServletResponse response) {
        log.debug("addPackageMobile");
        String send_name = request.getParameter("send_name");
        String send_phone = request.getParameter("send_phone");
        String send_personId = request.getParameter("send_personId");
        String recv_name = request.getParameter("recv_name");
        String recv_phone = request.getParameter("recv_phone");
        String recv_addr = request.getParameter("recv_addr");
        Map<String, String> mainMap1 = new HashMap<>();
        mainMap1.put("send_name", send_name);
        mainMap1.put("send_phone", send_phone);
        mainMap1.put("send_personId", send_personId);
        mainMap1.put("recv_name", recv_name);
        mainMap1.put("recv_phone", recv_phone);
        mainMap1.put("recv_addr", recv_addr);
        String msg = JsonBuildUtil.packToObject(mainMap1, null, null);
        Dynamicqr dynamicqr = new Dynamicqr();
        String personId= "";
        log.debug(recv_name);
        Person person = userService.getPersonByUserName(recv_phone);
        if (person != null) personId = person.getPersonId();
        if (person == null) {
            person = new Person();
            person.setIdCrl("2");
            person.setPhone(recv_phone);
            person.setLogNm(recv_phone);
            person.setGender("2");
            person.setName(recv_name);
            person.setDeleteFlag("0");
            person.setLogPw("E10ADC3949BA59ABBE56E057F20F883E");
            personId = IdBuilder.getPersonId();
            person.setPersonId(personId);
            personMapper.insert(person);
        }
        Package pac = new Package();
        String pacId = IdBuilder.getPackageId();
        dynamicqr.setPacid(pacId);
        dynamicqr.setMsg(msg);
        dynamicqrMapper.insertSelective(dynamicqr);
        pac.setPacId(pacId);
        pac.setDeleteFlag("0");
        pac.setPosId("1454409149144");
        pac.setSendId(send_personId);
        pac.setRecId(personId);
        pac.setPacStatus("0");
        List<Address> addressList = userService.getAddress(personId);
        log.debug("personId: " + personId);
        Address address = new Address();
        if (addressList.isEmpty()) {
            log.debug("addressList is empty");
            address.setAddrInfo(recv_addr);
            address.setPersonId(person.getPersonId());
            address.setDeleteFlag("0");
            addressMapper.insert(address);
            pac.setAddrId(address.getAddrId());
        } else {
            log.debug("addressList is not empty");
            for (Address addr : addressList) {
                if (addr.getAddrInfo().equals(recv_addr)) {
                    pac.setAddrId(addr.getAddrId());
                }
            }
        }
        packageMapper.insertSelective(pac);
        log.debug("new pac");
        Map<String, String> mainMap = new HashMap<>();
        mainMap.put("pacId", pac.getPacId());
        String json = JsonBuildUtil.packToObject(mainMap, null, null);
        log.debug(json);
        PublicMethod.SendJsonToFront(response, json);
    }

    /**
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "packageInfoSpec", method = RequestMethod.GET)
    public void packageInfoSpec(HttpServletRequest request, HttpServletResponse response) {
        log.debug("query by pacId");
        String pacId = request.getParameter("pacId");
        log.debug(pacId);
        List<QueryDto> list;
        list = userService.getPackageInfoByPacId(pacId);
        String json = PublicMethod.getAjaxResponse(list, "queryDto");
        log.debug("query result in json type: " + json);
        PublicMethod.SendJsonToFront(response, json);
    }

    /**
     * 快件状态改为运送途中
     * @param request
     * @param response
     */
    @RequestMapping(value = "changePacStatusSend", method = RequestMethod.GET)
    public void changePacStatusSend(HttpServletRequest request, HttpServletResponse response) {
        log.debug("changePacStatusSend");
        String pacId = request.getParameter("pacId");
        Package record = new Package();
        record = packageMapper.selectByPrimaryKey(pacId);
        record.setPacStatus("1");
        packageMapper.updateByPrimaryKeySelective(record);
        String json = "{\"status\":\"success\"}";
        PublicMethod.SendJsonToFront(response, json);
    }

    /**
     * 快件状态改为等待收件
     * @param request
     * @param response
     */
    @RequestMapping(value = "changePacStatusRecv", method = RequestMethod.GET)
    public void changePacStatusRecv(HttpServletRequest request, HttpServletResponse response) {
        log.debug("changePacStatusRecv");
        String pacId = request.getParameter("pacId");
        Package record = new Package();
        record.setPacStatus("2");
        log.debug(record.getPacStatus());
        log.debug("here test");
        record.setPacId(pacId);
        packageMapper.updateByPrimaryKeySelective(record);
        log.debug("here test");
        String json = "{\"status\":\"success\"}";
        PublicMethod.SendJsonToFront(response, json);
    }

    @RequestMapping(value = "payTheBill", method = RequestMethod.GET)
    public void setPayStatus(HttpServletResponse response) {
        log.debug("payTheBill");
        payStatus = 1;
        String str = "{\"status\":\"success\"}";
        PublicMethod.sendStringToFront(response, str);
    }

    @RequestMapping(value = "checkTheBill", method = RequestMethod.GET)
    public void getPayStatus(HttpServletResponse response) {
        log.debug("checkTheBill");
        String str = "";
        if (payStatus == 1) {
            str = "{\"status\":\"ok\"}";
        } else {
            str = "{\"status\":\"fail\"}";
        }
        payStatus = 0;
        PublicMethod.sendStringToFront(response, str);
    }

    @RequestMapping(value = "getQRContent", method = RequestMethod.GET)
    public void getQRContent(HttpServletRequest request, HttpServletResponse response) {
        log.debug("getQRContent");
        String pacId = request.getParameter("pacId");
        Dynamicqr dynamicqr = dynamicqrMapper.selectByPrimaryKey(pacId);
        log.debug(dynamicqr.getMsg());
        PublicMethod.sendStringToFront(response, dynamicqr.getMsg());
    }
}
