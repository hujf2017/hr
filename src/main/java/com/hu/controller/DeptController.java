package com.hu.controller;

import com.hu.pojo.Dept;
import com.hu.service.DeptService;
import com.hu.service.HrmService;
import com.hu.util.common.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: xupc
 * @Date: 2019/4/22 13:23
 * @Description:
 */

@Controller
public class DeptController {
    /**
     * deptService
     * */
    @Autowired
    private DeptService deptService;


    /*
     * @Author xupc
     * @Date 2019/4/22 13:49
     * @return
     * 涉及到查询所有的都进行分页处理
     **/
    @RequestMapping(value="/dept/selectDept")
    public String selectDept(Model model,Integer pageIndex,
                             @ModelAttribute Dept dept){
        PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }

        /** 查询部门信息     */
        List<Dept> depts = deptService.findDept(dept, pageModel);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel", pageModel);
        return "dept/dept";

    }
    @RequestMapping(value="/dept/updateDept")
    public ModelAndView updateDpet(ModelAndView mv , String flag,@ModelAttribute Dept dept){
        if(flag.equals("1")){
            // 根据id查询部门
            Dept target = deptService.findDeptById(dept.getId());
            // 设置Model数据
            mv.addObject("dept", target);
            // 设置跳转到修改页面
            mv.setViewName("dept/showUpdateDept");
        }else {
            // 执行修改操作
            deptService.modifyDept(dept);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/dept/selectDept");
        }
    // 返回
        return mv;
    }
    @RequestMapping(value="/dept/addDept")
    public ModelAndView addDept(ModelAndView mv,String flag,@ModelAttribute Dept dept){
        if(flag.equals("1")){
            mv.setViewName("dept/showAddDept");
        }else{
            deptService.addDept(dept);
            mv.setViewName("redirect:/dept/selectDept");
        }
        return mv;
    }
    @RequestMapping(value="/dept/removeDept")
    public  ModelAndView removeDept(ModelAndView mv,String ids){
        deptService.removeDept(Integer.parseInt(ids));
        mv.setViewName("redirect:/dept/selectDept");
        return mv;
    }
}
