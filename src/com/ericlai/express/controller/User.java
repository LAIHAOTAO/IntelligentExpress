package com.ericlai.express.controller;

import com.ericlai.express.model.QueryModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.enterprise.inject.Model;

/**
 * Created by ERIC_LAI on 15/11/29.
 */

@Controller
public class User {

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String getUser() {
        return "user";
    }

    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String getTableInfo(QueryModel queryModel, ModelMap model) {
        String packageNo = queryModel.getPackageNo();
        String sendPhone = queryModel.getSendPhone();
        String receivePhone = queryModel.getReceivePhone();

        model.addAttribute("packageNo", packageNo);
        model.addAttribute("sendPhone", sendPhone);
        model.addAttribute("receivePhone", receivePhone);
        return "user";
    }
}
