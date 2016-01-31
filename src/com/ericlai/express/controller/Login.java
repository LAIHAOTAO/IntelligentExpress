package com.ericlai.express.controller;

import com.ericlai.express.common.Constant;
import com.ericlai.express.dto.Person;
import com.ericlai.express.model.LoginModel;
import com.ericlai.express.service.LoginServiceImpl;
import com.ericlai.express.util.JsonBuildUtil;
import com.ericlai.express.util.MD5Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class Login {

	private static Logger log = LogManager.getLogger(Login.class.getName());

	@Resource
	private LoginServiceImpl loginService;

	//请求登录页面
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		log.debug("login");
		return "login";
	}

	//请求登录操作
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public String check(LoginModel login, Model model, HttpSession session) {
		log.debug("login check begin");
		String userName = login.getUserName();
		String password = login.getPassword();
		password = MD5Util.getMD5String(password);
		String role = login.getRole();
		log.debug("userName: " + userName);
		log.debug("password: " + password);
		log.debug("role: " + role);
		//检测用户名和密码是否为空
		if (userName == null || role == null) {
			model.addAttribute("result", Constant.U_P_R_NULL);
		}else {
			boolean	pwIsRight = checkRight(userName, password);
			if (pwIsRight) {
				Person person = loginService.getPersonByUserName(userName);
				//成功登录
				//在session当中,设置user为当前的用户名,作为已经登录的标记
				session.setAttribute("user", userName);
				//将姓名放到model当中返回给前端页面
				String name = person.getName();
				String gender = "";
				if (person.getGender().equals(Constant.MALE)) {
					gender = "先生";
				}else if(person.getGender().equals(Constant.FEMALE)){
					gender = "小姐";
				}
				model.addAttribute("name", name);
				model.addAttribute("gender", gender);
				if (Objects.equals(role, Constant.ADMIN)) {
					return "sysManager";
				} else if (Objects.equals(role, Constant.POSTMAN)) {
					return "postman";
				} else if (Objects.equals(role, Constant.USER)) {
					log.debug("reutrn user");
					return "user";
				}
			}else {
				model.addAttribute("result", Constant.PASSWORD_MISTAKE);
			}
		}
		return "login";
	}

	public boolean checkRight(String name, String password) {
		log.debug("Login checkRight");
		String rightPw = loginService.getPwByUserName(name);
		log.debug("rightPw is: " + rightPw);
		return password != null && !password.equals("") && password.equals(rightPw);
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
