package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * MediaX
 */
@NoArgsConstructor
@Data
public class MediaX {
    /**
     * displayUrl
     */
    private String displayUrl;
    /**
     * type
     */
    private String type;
    /**
     * mediaUrl
     */
    private String mediaUrl;
    /**
     * url
     */
    private String url;
    /**
     * originalInfo
     */
    private OriginalInfoX originalInfo;
    /**
     * features
     */
    private FeaturesX features;
    /**
     * indices
     */
    private List<Integer> indices;
    /**
     * sizes
     */
    private SizesX sizes;
    /**
     * idStr
     */
    private String idStr;
    /**
     * expandedUrl
     */
    private String expandedUrl;
    /**
     * mediaUrlHttps
     */
    private String mediaUrlHttps;
    /**
     * id
     */
    private Long id;
}
