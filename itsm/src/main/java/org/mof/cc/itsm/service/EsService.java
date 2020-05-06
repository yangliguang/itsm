package org.mof.cc.itsm.service;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.mof.cc.itsm.entity.OperLog;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangliguang
 * @since 2020年4月30日下午4:14:31
 */
public interface EsService {
	//新建索引
			public void createIndex(String index) throws IOException;
			//判断索引是否存在
			public boolean isIndexExists(String index) throws IOException;
			//增加记录到es
			public void add(String index, String type, OperLog operLog)  throws IOException;
			
			//判断记录是否存在
			public boolean isExists(String index, String type, OperLog operLog)  throws IOException;
			
			//获取记录
			public void get(String index, String type, int id)  throws IOException;
			
			//更新记录
			public void update(String index, String type, OperLog operLog)  throws IOException;
			
			//删除记录
			public void delete(String index, String type, int id)  throws IOException;
			
			//搜索
			public void search(String index, String type, String title)  throws IOException;
}
