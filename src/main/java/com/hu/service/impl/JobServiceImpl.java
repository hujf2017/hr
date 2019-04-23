package com.hu.service.impl;

import com.hu.dao.JobDao;
import com.hu.pojo.Job;
import com.hu.service.JobService;
import com.hu.util.common.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xupc
 * @Date: 2019/4/22 18:49
 * @Description:
 */

@Service
public class JobServiceImpl implements JobService {
    @Resource
    private JobDao jobDao;
    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {
        Map<String,Object> params = new HashMap<>();
        params.put("job",job);
        int recordCount = jobDao.count(params);
        pageModel.setRecordCount(recordCount);
        if(recordCount>0){
            /** 开始分页查询数据：查询第几页的数据 */
            params.put("pageModel", pageModel);
        }
        List Jobs = jobDao.selectByPage(params);
        return Jobs;
    }

    @Override
    public Job findJobById(Integer id) {
        return jobDao.selectById(id);
    }

    @Override
    public void modifyJob(Job job) {
         jobDao.modifyJob(job);
    }

    @Override
    public void removeDept(Integer id) {
        jobDao.deleteJob(id);
    }

    @Override
    public void addJob(Job job) {
        jobDao.insertJob(job);
    }

    @Override
    public List findAllJob() {
      return   jobDao.selectAll();
    }
}
