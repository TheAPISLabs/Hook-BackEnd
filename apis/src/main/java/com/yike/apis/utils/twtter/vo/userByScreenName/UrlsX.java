package com.yike.apis.utils.twtter.vo.userByScreenName;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * UrlsX
 */
@NoArgsConstructor
@Data
public class UrlsX {
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
