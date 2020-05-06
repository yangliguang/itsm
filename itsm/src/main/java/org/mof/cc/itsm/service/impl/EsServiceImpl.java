package org.mof.cc.itsm.service.impl;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkRequest;
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
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.mof.cc.itsm.entity.OperLog;
import org.mof.cc.itsm.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangliguang
 * @since 2020年4月30日下午4:14:48
 */
@Service
public class EsServiceImpl implements EsService {
	@Autowired
	private RestHighLevelClient client;

	// 新建索引
	public void createIndex(String index) throws IOException {
		CreateIndexRequest request = new CreateIndexRequest();
		CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
		System.out.println("createIndex: " + JSON.toJSONString(response));

	}

	// 判断索引是否存在
	public boolean isIndexExists(String index) throws IOException {
		GetIndexRequest request = new GetIndexRequest();
		boolean isExists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println("isIndexExists: " + isExists);
		return isExists;
	}

	// 增加记录到es
	public void add(String index, String type, OperLog operLog) throws IOException {
		IndexRequest request = new IndexRequest(index, type, operLog.getId().toString());
		request.source(JSON.toJSONString(operLog), XContentType.JSON);
		IndexResponse response = client.index(request, RequestOptions.DEFAULT);
		System.out.println("add: " + JSON.toJSONString(response));
	}

	// 判断记录是否存在
	public boolean isExists(String index, String type, OperLog operLog) throws IOException {

		GetRequest request = new GetRequest(index, type, operLog.getId().toString());
		request.fetchSourceContext(new FetchSourceContext(false));
		request.storedFields("_none_");
		boolean isExists = client.exists(request, RequestOptions.DEFAULT);
		System.out.println("isRecordExists: " + isExists);
		return isExists;
	}

	// 获取记录
	public void get(String index, String type, int id) throws IOException {
		GetRequest getRequest = new GetRequest(index, type, id + "");
		GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
		System.out.println("get: " + JSON.toJSONString(getResponse));
	}

	// 更新记录
	public void update(String index, String type, OperLog operLog) throws IOException {
		operLog.setTitle(operLog.getTitle() + "updated");
		UpdateRequest request = new UpdateRequest(index, type, operLog.getId().toString());
		request.doc(JSON.toJSONString(operLog), XContentType.JSON);
		UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
		System.out.println("update: " + JSON.toJSONString(updateResponse));
	}

	// 删除记录
	public void delete(String index, String type, int id) throws IOException {
		DeleteRequest deleteRequest = new DeleteRequest(index, type, id + "");
		DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
		System.out.println("delete: " + JSON.toJSONString(response));
	}

	// 搜索
	public void search(String index, String type, String title) throws IOException {
		BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
		boolBuilder.must(QueryBuilders.matchQuery("title", title)); // 这里可以根据字段进行搜索，must表示符合条件的，相反的mustnot表示不符合条件的
		// boolBuilder.must(QueryBuilders.matchQuery("id", tests.getId().toString()));
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(boolBuilder);
		sourceBuilder.from(0);
		sourceBuilder.size(100); // 获取记录数，默认10
		sourceBuilder.fetchSource(new String[] { "id", "title" }, new String[] {}); // 第一个是获取字段，第二个是过滤的字段，默认获取全部
		SearchRequest searchRequest = new SearchRequest(index);
		searchRequest.types(type);
		searchRequest.source(sourceBuilder);
		SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
		System.out.println("search: " + JSON.toJSONString(response));
		SearchHits hits = response.getHits();
		SearchHit[] searchHits = hits.getHits();
		for (SearchHit hit : searchHits) {
			System.out.println("search -> " + hit.getSourceAsString());
		}
	}

	// 批量操作
	/*
	 * public void bulk() throws IOException { // 批量增加 BulkRequest bulkAddRequest =
	 * new BulkRequest(); for (int i = 0; i < testsList.size(); i++) { tests =
	 * testsList.get(i); IndexRequest indexRequest = new IndexRequest(INDEX_TEST,
	 * TYPE_TEST, tests.getId().toString());
	 * indexRequest.source(JSON.toJSONString(tests), XContentType.JSON);
	 * bulkAddRequest.add(indexRequest); } BulkResponse bulkAddResponse =
	 * client.bulk(bulkAddRequest, RequestOptions.DEFAULT);
	 * System.out.println("bulkAdd: " + JSON.toJSONString(bulkAddResponse));
	 * search(INDEX_TEST, TYPE_TEST, "this");
	 * 
	 * // 批量更新 BulkRequest bulkUpdateRequest = new BulkRequest(); for (int i = 0; i
	 * < testsList.size(); i++) { tests = testsList.get(i);
	 * tests.setName(tests.getName() + " updated"); UpdateRequest updateRequest =
	 * new UpdateRequest(INDEX_TEST, TYPE_TEST, tests.getId().toString());
	 * updateRequest.doc(JSON.toJSONString(tests), XContentType.JSON);
	 * bulkUpdateRequest.add(updateRequest); } BulkResponse bulkUpdateResponse =
	 * client.bulk(bulkUpdateRequest, RequestOptions.DEFAULT);
	 * System.out.println("bulkUpdate: " + JSON.toJSONString(bulkUpdateResponse));
	 * search(INDEX_TEST, TYPE_TEST, "updated");
	 * 
	 * // 批量删除 BulkRequest bulkDeleteRequest = new BulkRequest(); for (int i = 0; i
	 * < testsList.size(); i++) { tests = testsList.get(i); DeleteRequest
	 * deleteRequest = new DeleteRequest(INDEX_TEST, TYPE_TEST,
	 * tests.getId().toString()); bulkDeleteRequest.add(deleteRequest); }
	 * BulkResponse bulkDeleteResponse = client.bulk(bulkDeleteRequest,
	 * RequestOptions.DEFAULT); System.out.println("bulkDelete: " +
	 * JSON.toJSONString(bulkDeleteResponse)); search(INDEX_TEST, TYPE_TEST,
	 * "this"); }
	 */

}
