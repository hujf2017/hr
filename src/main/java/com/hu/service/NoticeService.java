package com.hu.service;

import com.hu.pojo.Notice;
import com.hu.util.common.PageModel;

import java.util.List;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 13:36
 * @Description:
 */
public interface NoticeService {
    List selectNotice(Notice notice,PageModel pageModel);

    void deleteNotice(Integer id);

    void updateNotice(Notice notice);

    Notice selectNoticeById(Integer id);

    void addNotice(Notice notice);
}
