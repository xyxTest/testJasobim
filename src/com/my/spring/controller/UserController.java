package com.my.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.enums.UserTypeEnum;
import com.my.spring.model.User;
import com.my.spring.service.UserService;
import com.my.spring.utils.DataWrapper;

@Controller
@RequestMapping(value="api/user")
public class UserController {
	@Autowired
	UserService userService;
	
	/**
	 * 
	 * @param userName、password、realName   //必须
	 * @param email、tel可有可无
	 * 其他参数不需要，由程序指定，如日期，用户类型
	 * @return
	 */
	@RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> register(
    		@ModelAttribute User user) {
        return userService.register(user);
    }
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView Login(
    		HttpServletRequest request,
    		@RequestParam(value="username",required=true) String userName,
    		@RequestParam(value="password",required=true) String password) {
		ModelAndView model=new ModelAndView();
		NavigationController test=new NavigationController();
		test.mainPage();
		if(userService.login(userName, password).getToken()!=null){
		   model.setViewName("test");
		}else{
			model.setViewName("login");
			request.setAttribute("errorMsg", "用户名或密码错误，请重新登陆");
		}
        return model;
    }
	
	/**
	 * 
	 * @param realname、email、tel  //只修改这三个参数，且在非空的情况下修改，否则不修改
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> UpdateUser(
    		@ModelAttribute User user,
    		@RequestParam(value="token",required=true) String token) {
        return userService.updateUser(user, token);
    }
	
	//普通用户获取自己的个人详情
	@RequestMapping(value="/details", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<User> getUserDetails(
    		@RequestParam(value="token",required=true) String token) {
        return userService.getUserDetails(token);
    }
	
	
	//管理员获取其他用户的个人详情
	@RequestMapping(value="/admin/getUserDetails/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<User> getUserDetailsByAdmin(
    		@PathVariable(value="userId") Long userId,
    		@RequestParam(value="token",required=true) String token) {
        return userService.getUserDetailsByAdmin(userId,token);
    }
	
	//管理员获取用户列表
	@RequestMapping(value="/admin/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public DataWrapper<List<User>> getUserListByAdmin(
    		@RequestParam(value="pageIndex",required=false) Integer pageIndex,
    		@RequestParam(value="pageSize",required=false) Integer pageSize,
    		@RequestParam(value="token",required=true) String token) {
        return userService.getUserList(pageIndex,pageSize,token);
    }
	
	//修改用户权限
	@RequestMapping(value="/admin/changeUser/{userId}/type/{userType}", method = RequestMethod.POST)
    @ResponseBody
    public DataWrapper<Void> changeUserTypeByAdmin(
    		@PathVariable(value="userId") Long userId,
    		@PathVariable(value="userType") Integer userType,
    		@RequestParam(value="token",required=true) String token) {
		if (userType != 0 && userType != 1) {
			userType = UserTypeEnum.User.getType();
		}
        return userService.changeUserTypeByAdmin(userId,userType,token);
    }
	
	

}
