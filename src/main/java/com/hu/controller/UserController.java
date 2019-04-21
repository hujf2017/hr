package com.hu.controller;

import com.hu.pojo.User;
import com.hu.service.HrmService;
import com.hu.util.common.HrmConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/*
 * @Author xupc
 * @Date 2019/4/21 19:45
 * @return
 *
 * 人员控制
 **/
@Controller
public class UserController {
    /**
     * 自动注入UserService
     * */
    @Autowired
    private HrmService hrmService;
    /*
     * @Author xupc
     * @Date 2019/4/21 19:47
     * @return  登陆界面 model
     * 传入 用户名和密码
     **/
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpSession session, @RequestParam("loginname")String loginname, @RequestParam("password")String password, ModelAndView mv){
        // 调用业务逻辑组件判断用户是否可以登录
        System.out.println(loginname+password);
        User user = hrmService.login(loginname, password);
        if(user != null){
            // 将用户保存到HttpSession当中
            session.setAttribute(HrmConstants.USER_SESSION, user);
            // 客户端跳转到main页面
            mv.setViewName("redirect:/main");
        }else{
            // 设置登录失败提示信息
            mv.addObject("message", "登录名或密码错误!请重新输入");
            // 服务器内部跳转到登录页面
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }
}

