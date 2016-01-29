package com.ericlai.express.service;

import com.ericlai.express.dao.PersonMapper;
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
    private LoginService loginService;

    @Override
    public String getPwByUserName(String name) {
        return this.loginService.getPwByUserName(name);
    }
}
