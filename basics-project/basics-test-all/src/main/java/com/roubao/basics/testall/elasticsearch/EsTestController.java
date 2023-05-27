package com.roubao.basics.testall.elasticsearch;

import com.roubao.nosql.elasticsearch.bean.ElasticSearchAdapter;
import com.roubao.nosql.elasticsearch.enums.AOEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SongYanBin
 * @copyright ©2023-2099 SongYanBin. All rights reserved.
 * @since 2023/5/24
 **/
@Api(value = "/esTest", tags = "ES测试API")
@RestController
@RequestMapping("/esTest")
public class EsTestController {

    @Autowired
    private ElasticSearchAdapter elasticSearchAdapter;

    @ApiOperation("创建索引")
    @GetMapping("/createIndex")
    public SearchResponse test01() {
        return elasticSearchAdapter.boolSearch("syb_userinfo_index")
                .showFiled("name", "age")
                .termQuery("name", "SongYanBin", AOEnum.MUST)
                .rangeQuery("age", 19, 24, AOEnum.SHOULD)
                .open(AOEnum.MUST)
                .mustTermQuery("age", 24)
                .close()
                .orderBy("age", SortOrder.DESC)
                .doStartSearch();
    }
}
