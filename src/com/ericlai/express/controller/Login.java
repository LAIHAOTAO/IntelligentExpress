package com.ericlai.express.controller;

import com.ericlai.express.common.Constant;
import com.ericlai.express.common.PublicMethod;
import com.ericlai.express.model.LoginModel;
import com.ericlai.express.service.LoginServiceImpl;
import com.ericlai.express.util.JsonBuildUtil;
import com.ericlai.express.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@Configuration
@ComponentScan("com.ericlai.express.service")
public class Login {

	@Autowired
	private LoginServiceImpl loginService;

	//请求登录页面
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	//请求登录操作
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public String check(LoginModel login, Model model, HttpSession session) {
		String userName = login.getUserName();
		String password = login.getPassword();
		password = MD5Util.getMD5String(password);
		String role = login.getRole();
		//检测用户名和密码是否为空
		if (userName == null || role == null) {
			model.addAttribute("result", Constant.U_P_R_NULL);
		}else {
			boolean	pwIsRight = PublicMethod.checkRight(userName, password);
			if (pwIsRight) {
				//成功登录
				//在session当中,设置user为当前的用户名,作为已经登录的标记
				session.setAttribute("user", userName);
				//将姓名放到model当中返回给前端页面
				String name = loginService.getPersonByUserName(userName).getName();
				model.addAttribute("name", name);
				if (Objects.equals(role, Constant.ADMIN)) {
					return "redirect:sysManager";
				} else if (Objects.equals(role, Constant.POSTMAN)) {
					return "redirect:postman";
				} else if (Objects.equals(role, Constant.USER)) {
					return "redirect:user";
				}
			}else {
				model.addAttribute("result", Constant.PASSWORD_MISTAKE);
			}
		}
		return "login";
	}

	//请求注销操作
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public void logout(HttpServletResponse response) {
		//定义存放返回的json格式字符串
		String json = "";
		//调用工具类获取json格式字符串
		Map<String,String> param = new HashMap<>();
		param.put("status", "success");
		json = JsonBuildUtil.packToObject(param, null, null);
		//定义一个输出流
		PrintWriter out = null;
		System.out.println("logout");
		System.out.println(json);
		//设置格式
		response.setContentType("application/json");
		try {
			//获取输出的格式
			out = response.getWriter();
			//输出json格式的字符串到前端
			out.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
