package com.ericlai.express.dao;

import com.ericlai.express.dto.QueryDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */

@Repository
public interface QueryMapper {
    List<QueryDto> getPackageInfoByPhone(
            @Param("senderPhone")String senderPhone, @Param("recverPhone")String recverPhone);
//    List<String> getPackageIdByPhone(@Param("phone") String phone);
}
