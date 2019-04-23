package com.hu.controller;

import com.hu.pojo.Notice;
import com.hu.pojo.User;
import com.hu.service.NoticeService;
import com.hu.util.common.HrmConstants;
import com.hu.util.common.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 13:30
 * @Description:
 */

@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "/notice/selectNotice")
    public String selectNotice(Model model, Integer pageIndex, Notice notice ){
        PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List notices = noticeService.selectNotice(notice,pageModel);
        model.addAttribute("notices",notices);
        model.addAttribute("pagelModel",pageModel);
        return "notice/notice";
    }
    @RequestMapping(value = "/notice/removeNotice")
    public ModelAndView deleteNotice(String ids,ModelAndView mv){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id : idArray){
            // 根据id删除公告
            noticeService.deleteNotice(Integer.parseInt(id));
        }
        // 设置客户端跳转到查询请求
        mv.setViewName("redirect:/notice/selectNotice");
        // 返回ModelAndView
        return mv;
    }
    @RequestMapping(value = "/notice/updateNotice")
    public ModelAndView updateNotice(ModelAndView mv,@ModelAttribute Notice notice,String flag){
        if(flag.equals("1")){
            Notice notice1 = noticeService.selectNoticeById(notice.getId());
            mv.addObject("notice",notice1);
            mv.setViewName("/notice/showUpdateNotice");
        }else {
            noticeService.updateNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        return mv;
    }

    /**
     * 预览
     * @param Integer id  要显示的公告id
     * @param Model model
     * */
    @RequestMapping(value="/notice/previewNotice")
    public String previewNotice(
            Integer id,Model model){

        Notice notice = noticeService.selectNoticeById(id);

        model.addAttribute("notice", notice);
        // 返回
        return "notice/previewNotice";
    }


    /**
     * 处理添加请求
     * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
     * @param Notice notice  要添加的公告对象
     * @param ModelAndView mv
     * */
    @RequestMapping(value="/notice/addNotice")
    public ModelAndView addNotice(
            String flag,
            @ModelAttribute Notice notice,
            ModelAndView mv,
            HttpSession session){
        if(flag.equals("1")){
            mv.setViewName("notice/showAddNotice");
        }else{
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
            notice.setUser(user);
            noticeService.addNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        // 返回
        return mv;
    }

}
