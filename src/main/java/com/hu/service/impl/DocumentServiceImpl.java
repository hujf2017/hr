package com.hu.service.impl;

import com.hu.dao.DocumentDao;
import com.hu.pojo.Document;
import com.hu.service.DocumentService;
import com.hu.util.common.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 15:27
 * @Description:
 */

@Service
public class DocumentServiceImpl implements DocumentService {
    @Resource
     private DocumentDao documentDao;

    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        Map map = new HashMap();
        map.put("document",document);
        int recordCount = documentDao.count(map);
        pageModel.setRecordCount(recordCount);//定义模板最大数量
        if(recordCount>0){
            /** 开始分页查询数据：查询第几页的数据 */
            map.put("pageModel", pageModel);
        }
        List documents = documentDao.selectDocument(map);
        return documents;
    }

    @Override
    public void saveDocument(Document document) {
        documentDao.save(document);
    }

    @Override
    public Document findDocumentById(Integer id) {
        return documentDao.selectDocumentById(id);
    }

    @Override
    public Document modifyDocument(Document document) {

        return documentDao.updateDocument(document);
    }

    @Override
    public void deleteDocumentById(Integer id) {
        documentDao.deleteDocumentById(id);
    }


}
