package com.ericlai.express.dao;

import com.ericlai.express.dto.Address;
import com.ericlai.express.dto.QueryDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */

@Repository
public interface QueryMapper {

    /**
     * 通过手机号码获取快递信息
     * @param senderPhone
     * @param recverPhone
     * @return
     */
    List<QueryDto> getPackageInfoByPhone(
            @Param("senderPhone")String senderPhone, @Param("recverPhone") String recverPhone);

    /**
     * 通过登录名获取快递编号
     * @param userName
     * @return
     */
    List<QueryDto> getPackageIdByUserName(@Param("userName") String userName);

    /**
     * 通过快递ID获取快递信息
     * @param pacId
     * @return
     */
    List<QueryDto> getPackageInfoByPacId(@Param("pacId") String pacId);

    /**
     * 通过身份ID获取地址信息
     * @param personId
     * @return
     */
    List<Address> getAddress(@Param("personId") String personId);
}
