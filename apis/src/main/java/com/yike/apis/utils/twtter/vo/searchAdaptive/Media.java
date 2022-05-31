package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Media
 */
@NoArgsConstructor
@Data
public class Media {
    /**
     * ext
     */
    private Ext ext;
    /**
     * displayUrl
     */
    private String displayUrl;
    /**
     * extMediaColor
     */
    private ExtMediaColor extMediaColor;
    /**
     * type
     */
    private String type;
    /**
     * mediaUrl
     */
    private String mediaUrl;
    /**
     * extMediaAvailability
     */
    private ExtMediaAvailability extMediaAvailability;
    /**
     * extAltText
     */
    private ExtAltText extAltText;
    /**
     * url
     */
    private String url;
    /**
     * originalInfo
     */
    private OriginalInfo originalInfo;
    /**
     * features
     */
    private Features features;
    /**
     * indices
     */
    private List<Integer> indices;
    /**
     * sizes
     */
    private Sizes sizes;
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
    /**
     * mediaKey
     */
    private String mediaKey;
    /**
     * extSensitiveMediaWarning
     */
    private ExtSensitiveMediaWarning extSensitiveMediaWarning;
}
