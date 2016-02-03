package com.ericlai.express.service;

import com.ericlai.express.dao.PersonMapper;
import com.ericlai.express.dao.QueryMapper;
import com.ericlai.express.dto.Person;
import com.ericlai.express.dto.QueryDto;
import com.ericlai.express.util.GetBeanMap;
import com.ericlai.express.util.JsonBuildUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/30.
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private QueryMapper queryMapper;

    @Resource
    private PersonMapper personMapper;

    private Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    @Override
    public List<QueryDto> getPackageInfoByPhone(String senderPhone, String recverPhone) {
        return this.queryMapper.getPackageInfoByPhone(senderPhone, recverPhone);
    }

    @Override
    public List<QueryDto> getPackageIdByPhone(String phone) {
        return this.queryMapper.getPackageIdByPhone(phone);
    }

    @Override
    public List<QueryDto> getPackageInfoByPacId(String pacId) {
        return this.queryMapper.getPackageInfoByPacId(pacId);
    }

    @Override
    public int updateByPrimaryKeySelective(Person person) {
        return this.personMapper.updateByPrimaryKeySelective(person);
    }

    @Override
    public void updataLogPw(String oldPw, String newPw) {
        personMapper.updataLogPw(oldPw, newPw);
    }

    @Override
    public String getAjaxResponse(List<QueryDto> list) {
        log.debug("getAjaxResponse begin");
        ArrayList<String> subKey = new ArrayList<>();
        ArrayList<Map<String,String>> subValue = new ArrayList<>();
        Map<String, String> mainMap = new HashMap<>();
        mainMap.put("result", "success");
        subKey.add("queryDto");
        //遍历数据库查出来的所有记录
        for (QueryDto aList : list) {
            Map<String, String> utilMap = GetBeanMap.getBeanFieldAndValue(aList);
            subValue.add(utilMap);
        }
        return JsonBuildUtil.packToObject(mainMap,subKey,subValue);
    }

}
