package com.ericlai.express.service;

import com.ericlai.express.dto.QueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */
public interface UserService {
    List<QueryDto> getPackageInfoByPhone(String senderPhone, String recverPhone);
    String getAjaxResponse(List<QueryDto> list);
    List<QueryDto> getPackageIdByPhone(String phone);
    List<QueryDto> getPackageInfoByPacId(String pacId);
}
