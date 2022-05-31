package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entities
 */
@NoArgsConstructor
@Data
public class Entities {
    /**
     * hashtags
     */
    private List<Hashtags> hashtags;
    /**
     * media
     */
    private List<MediaX> media;
    /**
     * symbols
     */
    private List<?> symbols;
    /**
     * urls
     */
    private List<?> urls;
    /**
     * userMentions
     */
    private List<UserMentions> userMentions;
}
