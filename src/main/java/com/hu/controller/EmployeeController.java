package com.hu.controller;

import com.hu.dao.EmployeeDao;
import com.hu.pojo.Dept;
import com.hu.pojo.Employee;
import com.hu.pojo.Job;
import com.hu.service.DeptService;
import com.hu.service.EmployeeService;
import com.hu.service.JobService;
import com.hu.util.common.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 09:51
 * @Description:
 */

@Controller
public class EmployeeController {
    @Autowired
    private  EmployeeService employeeService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private JobService jobService;

    /*
     * @Author xupc
     * @Date 2019/4/23 9:56
     * @return
     * @param model :最终返回的字符串 设置model属性
     * @pageIndex: 需要配置分页 算出所有的数量
     **/
    @RequestMapping(value ="/employee/selectEmployee")
    public String selectEmployee(Model model,Integer pageIndex,Integer job_id,Integer dept_id,
                                 @ModelAttribute Employee employee){
//        // 模糊查询时判断是否有关联对象传递，如果有，创建并封装关联对象
        this.genericAssociation(job_id, dept_id, employee);
        PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);   //pageIndex :0
        }
        // 查询职位信息，用于模糊查询
        List<Job> jobs = jobService.findAllJob();
        // 查询部门信息 ，用于模糊查询
        List<Dept> depts = deptService.findAllDept();
        /** 查询员工信息     */
        List<Employee> employees =employeeService.findEmployee(employee, pageModel);
        model.addAttribute("employees", employees);
        model.addAttribute("jobs", jobs);
        model.addAttribute("depts", depts);
        model.addAttribute("pageModel", pageModel);

        return "employee/employee";
    }
    @RequestMapping(value ="/employee/updateEmployee")
    public ModelAndView updateEmployee(Integer job_id,Integer dept_id,ModelAndView mv,String flag,@ModelAttribute Employee employee){
        if(flag.equals("1")){
            // 根据id查询员工
            Employee target = employeeService.findEmployeeById(employee.getId());
            // 需要查询职位信息
            List<Job> jobs = jobService.findAllJob();
            // 需要查询部门信息
            List<Dept> depts = deptService.findAllDept();
            // 设置Model数据
            mv.addObject("jobs", jobs);
            mv.addObject("depts", depts);
            mv.addObject("employee", target);
            // 返回修改员工页面
            mv.setViewName("/employee/showUpdateEmployee");
        }else{
            // 创建并封装关联对象
            this.genericAssociation(job_id, dept_id, employee);
            // 执行修改操作
            employeeService.updateEmployee(employee);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/employee/selectEmployee");
        }
        return mv;
    }
    /*
     * @Author xupc
     * @Date 2019/4/23 12:30
     * @return
     **多选ids 先进行id划分
     * */
    @RequestMapping(value = "/employee/removeEmployee")
    public ModelAndView deleteEmployee(ModelAndView mv,String ids){
        // 分解id字符串
		String[] idArray = ids.split(",");
		for(String id :idArray){
            employeeService.deleteEmployee(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/employee/selectEmployee");
        return mv;
    }

    @RequestMapping(value = "/employee/addEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee,ModelAndView mv,String flag,Integer job_id,Integer dept_id){
      if(flag.equals("1")){
          // 查询职位信息
          List<Job> jobs = jobService.findAllJob();
          // 查询部门信息
          List<Dept> depts = deptService.findAllDept();
          // 设置Model数据
          mv.addObject("jobs", jobs);
          mv.addObject("depts", depts);
          // 返回添加员工页面
          mv.setViewName("employee/showAddEmployee");
      }else{
          // 判断是否有关联对象传递，如果有，创建关联对象
          this.genericAssociation(job_id, dept_id, employee);
          // 添加操作
          employeeService.saveEmployee(employee);
          // 设置客户端跳转到查询请求
          mv.setViewName("redirect:/employee/selectEmployee");
      }
        return mv;
    }

    /**
     * 由于部门和职位在Employee中是对象关联映射，
     * 所以不能直接接收参数，需要创建Job对象和Dept对象
     * */
    private void genericAssociation(Integer job_id,
                                    Integer dept_id,Employee employee){
        if(job_id != null){
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if(dept_id != null){
            Dept dept = new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }
}
