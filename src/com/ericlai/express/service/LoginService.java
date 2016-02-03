package com.ericlai.express.service;

import com.ericlai.express.dto.Person;

import java.util.Map;

/**
 * Created by Administrator on 2016/1/25.
 */
public interface LoginService {

    /**
     * 通过用户名获取密码
     * @param name
     * @return
     */
    String getPwByUserName(String name);

    /**
     * 通过用户名获取某人的所有信息
     * @param name
     * @return
     */
    Person getPersonByUserName(String name);

}
