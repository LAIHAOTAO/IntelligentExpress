package com.ericlai.express.service;

import com.ericlai.express.dto.Address;
import com.ericlai.express.dto.Person;
import com.ericlai.express.dto.QueryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */
public interface UserService {

    /**
     * 通过手机号码获取快递信息
     * @param senderPhone
     * @param recverPhone
     * @return
     */
    List<QueryDto> getPackageInfoByPhone(String senderPhone, String recverPhone);

    /**
     * 通过登录名获取快递编号
     * @param userName
     * @return
     */
    List<QueryDto> getPackageIdByUserName(String userName);

    /**
     * 通过快递ID获取快递信息
     * @param pacId
     * @return
     */
    List<QueryDto> getPackageInfoByPacId(String pacId);

    /**
     * 通过personId更新个人信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Person record);

    /**
     * 通过身份ID获取地址信息
     * @param personId
     * @return
     */
    List<Address> getAddress(String personId);

    /**
     * 通过登录名获取某人所有信息
     * @param name
     * @return
     */
    Person getPersonByUserName(String name);

    /**
     * 通过登录名获取密码
     * @param name
     * @return
     */
    String getPwByUserName(String name);

    /**
     * 获取Ajax请求回复的数据
     * @param list
     * @param type
     * @return
     */
    String getAjaxResponse(List<?> list, String type);

    /**
     * 通过addrId删除地址信息
     * @param addrId
     * @return
     */
    int addrDeleteByPrimaryKey(int addrId);
}
