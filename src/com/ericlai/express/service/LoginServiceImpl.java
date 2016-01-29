package com.ericlai.express.service;

import com.ericlai.express.dao.PersonMapper;
import com.ericlai.express.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/1/25.
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private PersonMapper personMapper;

    @Override
    public String getPwByUserName(String name) {
        return this.personMapper.getPwByUserName(name);
    }

    @Override
    public Person getPersonByUserName(String name) {
        return this.personMapper.getPersonByUserName(name);
    }

    @Override
    public String getString() {
        return "test";
    }


}
