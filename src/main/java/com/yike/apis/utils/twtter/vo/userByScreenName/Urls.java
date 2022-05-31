package com.yike.apis.utils.twtter.vo.userByScreenName;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Urls
 */
@NoArgsConstructor
@Data
public class Urls {
    /**
     * displayUrl
     */
    private String displayUrl;
    /**
     * expandedUrl
     */
    private String expandedUrl;
    /**
     * url
     */
    private String url;
    /**
     * indices
     */
    private List<Integer> indices;
}
