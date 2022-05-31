package com.yike.apis.service;

import com.yike.apis.utils.reponseUtil.ResponseData;

public interface MiniService {
    void setMarketcapitalizationData(String tagSlugs);

    void getGlassnode(String name,String url,String a,String cookie);

    void setFigureData();

    ResponseData getMarketCapitalization(String type);

    ResponseData getFigureData(String type, String time);

    ResponseData getGlassnodeFigureData(String methods, String type);

    void setMarketcapitalizationDataNFT();

    ResponseData getCmcData(String type,Integer start,Integer limit);

    void setCmcData();
}
