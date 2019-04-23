package com.hu.service;

import com.hu.pojo.Job;
import com.hu.util.common.PageModel;

import java.util.List;

/**
 * @Auther: xupc
 * @Date: 2019/4/22 18:49
 * @Description:
 */
public interface JobService {
    List<Job> findJob(Job job, PageModel pageModel);

    Job findJobById(Integer id);

    void modifyJob(Job job);

    void removeDept(Integer id);

    void addJob(Job job);

    List<Job> findAllJob();
}
