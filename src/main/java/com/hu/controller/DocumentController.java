package com.hu.controller;

import com.hu.pojo.Document;
import com.hu.pojo.User;
import com.hu.service.DocumentService;
import com.hu.util.common.HrmConstants;
import com.hu.util.common.PageModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Auther: xupc
 * @Date: 2019/4/23 15:22
 * @Description:
 */
@Controller
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @RequestMapping(value="/document/selectDocument")
    public String  selectDocument(Model model, Integer pageIndex,
                               @ModelAttribute Document document){
        PageModel pageModel = new PageModel();
        if(pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Document> documents = documentService.findDocument(document, pageModel);
        model.addAttribute("documents", documents);
        model.addAttribute("pageModel", pageModel);
        return  "document/document";
    }

    @RequestMapping(value="/document/addDocument")
    public ModelAndView addDocument(HttpSession session, String flag, @ModelAttribute Document document, ModelAndView mv) throws IOException {
        if(flag.equals("1")){
            mv.setViewName("document/showAddDocument");
        }else{
            String path = session.getServletContext().getRealPath("/upload/");
            System.out.println(path);
            // 上传文件名
            String fileName = document.getFile().getOriginalFilename();
            // 将上传文件保存到一个目标文件当中
            document.getFile().transferTo(new File(path+File.separator+ fileName));

            // 插入数据库
            // 设置fileName
            document.setFileName(fileName);
            // 设置关联的User对象
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
            document.setUser(user);
            // 插入数据库
            documentService.saveDocument(document);
            // 返回
            mv.setViewName("redirect:/document/selectDocument");
        }

        return mv;
    }
    @RequestMapping(value="/document/updateDocument")
    public ModelAndView updateDocument(String flag,@ModelAttribute Document document,ModelAndView mv){
        if(flag.equals("1")){
            Document document1 =documentService.findDocumentById(document.getId());
            mv.addObject("document",document1);
            mv.setViewName("/document/showUpdateDocument");
        }else {
            // 执行修改操作
            documentService.modifyDocument(document);
            // 设置客户端跳转到查询请求
            mv.setViewName("redirect:/document/selectDocument");
        }
        return mv;
    }
    @RequestMapping(value="/document/removeDocument")
    public ModelAndView deleteDocument(ModelAndView mv,String ids){
        // 分解id字符串
        String[] idArray = ids.split(",");
        for(String id :idArray){
            documentService.deleteDocumentById(Integer.parseInt(id));
        }
        mv.setViewName("redirect:/document/selectDocument");
        return mv;
    }

    @RequestMapping(value="/document/downLoad")
    public ResponseEntity<byte[]> downDocument(HttpSession httpSession, Integer id) throws IOException {
        // 根据id查询文档
        Document target = documentService.findDocumentById(id);
        String fileName = target.getFileName();
        // 上传文件路径
        String path = httpSession.getServletContext().getRealPath(
                "/upload/");
        // 获得要下载文件的File对象
        File file = new File(path+File.separator+ fileName);

        //下载从这里开始
        // 创建springframework的HttpHeaders对象
        HttpHeaders headers = new HttpHeaders();
        // 下载显示的文件名，解决中文名称乱码问题
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        // 通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName);
        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
