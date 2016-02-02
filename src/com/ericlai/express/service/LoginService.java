package com.ericlai.express.service;

import com.ericlai.express.dto.Person;

import java.util.Map;

/**
 * Created by Administrator on 2016/1/25.
 */
public interface LoginService {

    String getPwByUserName(String name);

    Person getPersonByUserName(String name);

}
