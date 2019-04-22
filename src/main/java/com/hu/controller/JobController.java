package com.hu.controller;

import com.hu.pojo.Job;
import com.hu.service.JobService;
import com.hu.util.common.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: xupc
 * @Date: 2019/4/22 18:41
 * @Description:
 */

@Controller
public class JobController {

    @Autowired
    private JobService jobService;
    @RequestMapping(value = " /job/selectJob")
    public String selectJob(Model model, Integer pageIndex,
                            @ModelAttribute Job job){
        PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List jobs=jobService.findJob(job,pageModel);
        model.addAttribute("jobs", jobs);
        model.addAttribute("pageModel", pageModel);
        return "job/job";
    }
    @RequestMapping(value = "/job/updateJob")
    public ModelAndView updateJob(String flag,@ModelAttribute Job job,ModelAndView mv){
        if(flag.equals("1")){
            // 根据id查询job
            Job target = jobService.findJobById(job.getId());
            // 设置Model数据
            mv.addObject("job", target);
            mv.setViewName("job/showUpdateJob");
        }else{
            // 执行修改操作
            jobService.modifyJob(job);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/job/selectJob");
        }
        return mv;
    }

    @RequestMapping(value="/job/removeJob")
    public  ModelAndView removeJob(ModelAndView mv,String ids){
        jobService.removeDept(Integer.parseInt(ids));
        mv.setViewName("redirect:/job/selectJob");
        return mv;
    }
    @RequestMapping(value="/job/addJob")
    public ModelAndView addJob(String flag,ModelAndView mv,@ModelAttribute Job job){
        if(flag.equals("1")){
            mv.setViewName("/job/showAddJob");
        }else{
            // 执行添加操作，保存
            jobService.addJob(job);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/job/selectJob");
        }
        return mv;
    }


}
