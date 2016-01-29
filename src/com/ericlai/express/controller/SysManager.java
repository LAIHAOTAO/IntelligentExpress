package com.ericlai.express.controller;

import com.ericlai.express.model.PostmanModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ERIC_LAI on 15/11/27.
 */

@Controller
public class SysManager {

    @RequestMapping(value = "sysManager", method = RequestMethod.GET)
    public String sysManager() {
        return "sysManager";
    }

    @RequestMapping(value = "addPostman", method = RequestMethod.POST)
    public String addPostman(PostmanModel postmanModel, Model model) {
        System.out.println(postmanModel.getName());
        System.out.println(postmanModel.getPhone());
        //TODO: 添加邮递员的逻辑
        return "sysManger";
    }

    @RequestMapping(value = "managePostman", method = RequestMethod.GET)
    public String getManagePostman() {
        //TODO: 管理邮递员的逻辑
        return "managePostman";
    }

}