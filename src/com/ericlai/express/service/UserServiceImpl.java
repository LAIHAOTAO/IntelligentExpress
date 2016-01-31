package com.ericlai.express.service;

import com.ericlai.express.dao.QueryMapper;
import com.ericlai.express.dto.QueryDto;
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

    private Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    @Override
    public List<QueryDto> getPackageInfoByPhone(String senderPhone, String recverPhone) {
        return this.queryMapper.getPackageInfoByPhone(senderPhone, recverPhone);
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
        log.debug("list size: " + list.size());
        for (QueryDto aList : list) {
            log.debug("pacId: " + aList.getPacId());
            Map<String, String> utilMap = getQueryBeanMap(aList);
            subValue.add(utilMap);
        }
        return JsonBuildUtil.packToObject(mainMap,subKey,subValue);
    }

    private Map<String,String> getQueryBeanMap(QueryDto queryDto) {
        Map<String, String> map = new HashMap<>();
        String pacId = queryDto.getPacId();
        String senderName = queryDto.getSenderName();
        String sendPhone = queryDto.getSendPhone();
        String recverName = queryDto.getRecverName();
        String recverPhone = queryDto.getRecverPhone();
        String posterName = queryDto.getPosterName();
        String posterPhone = queryDto.getPosterPhone();
        String pacStatus = queryDto.getPacStatus();
        map.put("pacId", pacId);
        map.put("senderName", senderName);
        map.put("sendPhone", sendPhone);
        map.put("recverName", recverName);
        map.put("recverPhone", recverPhone);
        map.put("posterName", posterName);
        map.put("posterPhone", posterPhone);
        map.put("pacStatus", pacStatus);
        return map;
    }
}
