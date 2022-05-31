package com.yike.apis.service;

import com.yike.apis.pojo.mini.vo.SearchVo;
import com.yike.apis.pojo.search.SearchTag;
import com.yike.apis.utils.reponseUtil.ResponseData;

import java.util.List;

public interface SearchService {
    ResponseData setSearchHeat(SearchVo searchVo);

    ResponseData getSearchHeat(Integer limit);

    void clearCacheSynchronizeData();

    ResponseData getTag(String address);

    ResponseData setTag_address(String address, List<SearchTag> tagList);
}
