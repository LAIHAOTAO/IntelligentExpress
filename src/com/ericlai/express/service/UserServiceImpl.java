package com.ericlai.express.service;

import com.ericlai.express.dao.AddressMapper;
import com.ericlai.express.dao.PersonMapper;
import com.ericlai.express.dao.QueryMapper;
import com.ericlai.express.dto.Address;
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

    @Resource
    private AddressMapper addressMapper;

    private Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    @Override
    public List<QueryDto> getPackageInfoByPhone(String senderPhone, String recverPhone) {
        return this.queryMapper.getPackageInfoByPhone(senderPhone, recverPhone);
    }

    @Override
    public List<QueryDto> getPackageIdByUserName(String userName) {
        return this.queryMapper.getPackageIdByUserName(userName);
    }

    @Override
    public List<QueryDto> getPackageInfoByPacId(String pacId) {
        return this.queryMapper.getPackageInfoByPacId(pacId);
    }

    @Override
    public int updateByPrimaryKeySelective(Person record) {
        return this.personMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Address> getAddress(String personId) {
        return this.queryMapper.getAddress(personId);
    }

    @Override
    public Person getPersonByUserName(String name) {
        return this.personMapper.getPersonByUserName(name);
    }

    @Override
    public String getPwByUserName(String name) {
        return this.personMapper.getPwByUserName(name);
    }

    @Override
    public String getAjaxResponse(List<?> list, String type) {
        log.debug("getAjaxResponse begin");
        ArrayList<String> subKey = new ArrayList<>();
        ArrayList<Map<String,String>> subValue = new ArrayList<>();
        Map<String, String> mainMap = new HashMap<>();
        mainMap.put("result", "success");
        subKey.add(type);
        //遍历数据库查出来的所有记录
        for (Object aList : list) {
            Map<String, String> utilMap = GetBeanMap.getBeanFieldAndValue(aList);
            subValue.add(utilMap);
        }
        return JsonBuildUtil.packToObject(mainMap,subKey,subValue);
    }

    @Override
    public int addrDeleteByPrimaryKey(int addrId) {
        return this.addressMapper.deleteByPrimaryKey(addrId);
    }

}
