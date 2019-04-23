package com.hu.dao;

import com.hu.dao.provider.DocumentDynaSqlProvider;
import com.hu.pojo.Document;
import com.hu.util.common.PageModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.hu.util.common.HrmConstants.DOCUMENTTABLE;

/**
 * @Auther: xupc
 * @Date: 2019/4/23 15:31
 * @Description:
 */
public interface DocumentDao {

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "selectWhitParam")
    List<Document> selectDocument(Map<String, Object> map);

    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> map);

    // 动态插入文档
    @SelectProvider(type=DocumentDynaSqlProvider.class,method="insertDocument")
    void save(Document document);

    @Select("select * from "+DOCUMENTTABLE+" where id = #{id}")
    Document  selectDocumentById(Integer id);

    // 动态插入文档
    @SelectProvider(type=DocumentDynaSqlProvider.class,method="updateDocument")
    Document updateDocument(Document document);

    @Delete("delete from "+DOCUMENTTABLE+" where id = #{id}")
    void deleteDocumentById(Integer id);


}