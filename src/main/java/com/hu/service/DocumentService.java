package com.hu.service;

import com.hu.pojo.Document;
import com.hu.util.common.PageModel;

import java.util.List;
import java.util.Map;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 15:27
 * @Description:
 */
public interface DocumentService {
    List<Document> findDocument(Document document, PageModel pageModel);

    void  saveDocument(Document document);

    Document findDocumentById(Integer id);

    Document modifyDocument(Document document);

    void deleteDocumentById(Integer id);
}
