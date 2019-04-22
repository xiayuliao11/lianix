package com.lsl.controller;

import com.alibaba.fastjson.JSONObject;
import com.lsl.bean.Good;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("elastic")
public class ElasticController {
    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    @ResponseBody
    @RequestMapping("queryGood")
    public List<Good> queryGood(String queryInfo){
        //拿到elastic客户端
        Client client = elasticsearchTemplate.getClient();
        //参数为索引名称
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("good")
                .setTypes("elgood")
                //设置查询条件boolquery()多条件查询"goodInfo","goodName","operator",queryInfo
                .setQuery(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("goodname",queryInfo))
                        .should(QueryBuilders.matchQuery("goodvender",queryInfo)));
        //.setQuery(QueryBuilders.matchQuery("goodInfo", queryInfo));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("goodname");
        highlightBuilder.field("goodvender");
        //改变高亮字段标签
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        //设置高亮查询
        searchRequestBuilder.highlighter(highlightBuilder);
        //执行查询 拿到返回值
        SearchResponse searchResponse = searchRequestBuilder.get();
        System.out.println(searchResponse);
        //拿到命中条数
        SearchHits hits = searchResponse.getHits();
        //获取总条数 用来分页
        //hits.getTotalHits();
        //获取到结果集迭代器
        Iterator<SearchHit> iterator = hits.iterator();
        List<Good> goodEsList = new ArrayList<>();
        while (iterator.hasNext()){
            SearchHit next = iterator.next();
            //获取到源码内容 以json字符串的形式获取
            String sourceAsString = next.getSourceAsString();
            Good goodEs = JSONObject.parseObject(sourceAsString, Good.class);
            //获取高亮字段
            Map<String, HighlightField> highlightFields = next.getHighlightFields();

            //使用高亮内容 替换非高亮内容
            HighlightField goodname = highlightFields.get("goodname");
            if(goodname!=null) {
                Text[] text = goodname.fragments();
                String goodna = "";
                for (Text text3 : text) {
                    goodna += text3;
                }
                goodEs.setGoodName(goodna);
            }
            HighlightField opera = highlightFields.get("goodvender");
            if(opera!=null) {
                Text[] text4 = opera.fragments();
                String oper = "";
                for (Text tex : text4) {
                    oper += tex;
                }
                goodEs.setGoodVender(oper);
            }

            // System.out.println(goodEs.getGoodInfo());
            goodEsList.add(goodEs);
        }
        System.out.println(goodEsList);
        return  goodEsList;

    }
}
