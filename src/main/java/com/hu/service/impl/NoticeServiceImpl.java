package com.hu.service.impl;

import com.hu.dao.NoticeDao;
import com.hu.pojo.Notice;
import com.hu.service.NoticeService;
import com.hu.util.common.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 13:37
 * @Description:
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeDao noticeDao;
    @Override
    public List selectNotice(Notice notice, PageModel pageModel) {
        Map map = new HashMap();
        map.put("notice",notice);
        int recordCount = noticeDao.count(map);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            /** 开始分页查询数据：查询第几页的数据 */
            map.put("pageModel", pageModel);
        }
        List notices =noticeDao.selectNotice(map);
        return notices;
    }

    public void deleteNotice(Integer id){
        noticeDao.deleteNotice(id);
    }

    @Override
    public void updateNotice(Notice notice) {
        noticeDao.updateNotice(notice);
    }

    @Override
    public Notice selectNoticeById(Integer id) {
        return noticeDao.selectNoticeById(id);
    }

    @Override
    public void addNotice(Notice notice) {
        noticeDao.addNotice(notice);
    }

}
