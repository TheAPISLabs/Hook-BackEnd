package com.yike.apis.service;

import com.yike.apis.utils.reponseUtil.ResponseData;

public interface TrachService {
    ResponseData tokenbalance(String address);

    ResponseData tokentrans(String address, String tokenAddress, String start, String limit);

    void setPrice();

    Object maincoinexchange();

    Object normal(String address, String start, String limit);

    ResponseData getTag(String address);
}
