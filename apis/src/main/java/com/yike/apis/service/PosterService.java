package com.yike.apis.service;

import com.yike.apis.utils.reponseUtil.ResponseData;
import org.springframework.stereotype.Service;

public interface PosterService {

    ResponseData getPoster(String address);
}
