package com.hu.controller;

import com.hu.pojo.User;
import com.hu.service.HrmService;
import com.hu.util.common.HrmConstants;
import com.hu.util.common.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @RequestMapping(value = "/user/selectUser")
    public String selectUser( @ModelAttribute User user,
                              Model model,Integer pageIndex){
        System.out.println("user = " + user);
        PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        /** 查询用户信息     */
        List<User> users = hrmService.findUser(user, pageModel);
        model.addAttribute("users", users);
        model.addAttribute("pageModel", pageModel);
        return "user/user";
    }

    /*
     * @Author xupc
     * @Date 2019/4/22 10:42
     * @return
     * @param :传递过来的是id，通过id获取用户
     * String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
     **/
    @RequestMapping(value = "/user/updateUser")
    public ModelAndView updateUser(String flag,
                             @ModelAttribute User user,
                             ModelAndView mv){
        if(flag.equals("1")){
            // 根据id查询用户
            User target = hrmService.findUserById(user.getId());
            // 设置Model数据
            mv.addObject("user", target);
            // 返回修改员工页面
            mv.setViewName("user/showUpdateUser");
        }else{
            // 执行修改操作
                hrmService.modifyUser(user);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/user/selectUser");
        }
        // 返回
        return mv;
    }


    @RequestMapping(value = "/user/addUser")
    public ModelAndView User(String flag,
                                   @ModelAttribute User user,
                                   ModelAndView mv){
        if(flag.equals("1")){
            mv.setViewName("/user/showAddUser");  //跳转添加
        }else{
            // 执行添加操作，保存
            hrmService.addUser(user);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/user/selectUser");
        }
        return mv;
    }

    /**
     * 处理删除用户请求
     * @param  ids 需要删除的id字符串
     * @param  mv
     * */
    @RequestMapping(value="/user/removeUser")
    public ModelAndView removeUser(String ids,ModelAndView mv){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除员工
            hrmService.removeUserById(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/user/selectUser");
        // 返回ModelAndView
        return mv;
    }

//    public Model


}

