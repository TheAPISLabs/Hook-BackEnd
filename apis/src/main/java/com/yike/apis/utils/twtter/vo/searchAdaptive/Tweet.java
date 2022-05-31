package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;

@Data
public class Tweet {
    private String createdAt;
    private String mediaUrl;
    private String url;
    private String fullText;
    private String id;
    private String name;
    private String screenName;
    private String userId;
    private String profileImageUrlHttps;
}
