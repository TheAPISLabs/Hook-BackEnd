package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Tweets
 */
@NoArgsConstructor
@Data
public class Tweets {
    /**
     * selfThread
     */
    private SelfThread selfThread;
    /**
     * extendedEntities
     */
    private ExtendedEntities extendedEntities;
    /**
     * inReplyToStatusIdStr
     */
    private InReplyToStatusIdStr inReplyToStatusIdStr;
    /**
     * inReplyToStatusId
     */
    private InReplyToStatusId inReplyToStatusId;
    /**
     * supplementalLanguage
     */
    private SupplementalLanguage supplementalLanguage;
    /**
     * createdAt
     */
    private String createdAt;
    /**
     * inReplyToUserIdStr
     */
    private InReplyToUserIdStr inReplyToUserIdStr;
    /**
     * source
     */
    private String source;
    /**
     * possiblySensitiveEditable
     */
    private Boolean possiblySensitiveEditable;
    /**
     * retweetCount
     */
    private Integer retweetCount;
    /**
     * retweeted
     */
    private Boolean retweeted;
    /**
     * geo
     */
    private Geo geo;
    /**
     * inReplyToScreenName
     */
    private InReplyToScreenName inReplyToScreenName;
    /**
     * isQuoteStatus
     */
    private Boolean isQuoteStatus;
    /**
     * userIdStr
     */
    private String userIdStr;
    /**
     * fullText
     */
    private String fullText;
    /**
     * conversationId
     */
    private Long conversationId;
    /**
     * idStr
     */
    private String idStr;
    /**
     * inReplyToUserId
     */
    private InReplyToUserId inReplyToUserId;
    /**
     * favoriteCount
     */
    private Integer favoriteCount;
    /**
     * id
     */
    private Long id;
    /**
     * place
     */
    private Place place;
    /**
     * lang
     */
    private String lang;
    /**
     * quoteCount
     */
    private Integer quoteCount;
    /**
     * conversationIdStr
     */
    private String conversationIdStr;
    /**
     * favorited
     */
    private Boolean favorited;
    /**
     * ext
     */
    private ExtX ext;
    /**
     * possiblySensitive
     */
    private Boolean possiblySensitive;
    /**
     * coordinates
     */
    private Coordinates coordinates;
    /**
     * truncated
     */
    private Boolean truncated;
    /**
     * replyCount
     */
    private Integer replyCount;
    /**
     * entities
     */
    private Entities entities;
    /**
     * userId
     */
    private Long userId;
    /**
     * displayTextRange
     */
    private List<Integer> displayTextRange;
    /**
     * contributors
     */
    private Contributors contributors;

    /**
     * name
     */
    private String name;

    /**
     * screenName
     */
    private String screenName;

    /**
     * profileImageUrlHttps
     */
    private String profileImageUrlHttps;
}
