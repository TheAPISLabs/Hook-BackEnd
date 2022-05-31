package com.yike.apis.utils.twtter.vo.userByScreenName;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Legacy
 */
@NoArgsConstructor
@Data
public class Legacy {

    /**
     * blockedBy
     */
    private Boolean blockedBy;
    /**
     * blocking
     */
    private Boolean blocking;
    /**
     * canDm
     */
    private Boolean canDm;
    /**
     * canMediaTag
     */
    private Boolean canMediaTag;
    /**
     * createdAt
     */
    private String createdAt;
    /**
     * defaultProfile
     */
    private Boolean defaultProfile;
    /**
     * defaultProfileImage
     */
    private Boolean defaultProfileImage;
    /**
     * description
     */
    private String description;
    /**
     * entities
     */
    private EntitiesX entities;
    /**
     * fastFollowersCount
     */
    private Integer fastFollowersCount;
    /**
     * favouritesCount
     */
    private Integer favouritesCount;
    /**
     * followRequestSent
     */
    private Boolean followRequestSent;
    /**
     * followedBy
     */
    private Boolean followedBy;
    /**
     * followersCount
     */
    private Integer followersCount;
    /**
     * following
     */
    private Boolean following;
    /**
     * friendsCount
     */
    private Integer friendsCount;
    /**
     * hasCustomTimelines
     */
    private Boolean hasCustomTimelines;
    /**
     * isTranslator
     */
    private Boolean isTranslator;
    /**
     * listedCount
     */
    private Integer listedCount;
    /**
     * location
     */
    private String location;
    /**
     * mediaCount
     */
    private Integer mediaCount;
    /**
     * muting
     */
    private Boolean muting;
    /**
     * name
     */
    private String name;
    /**
     * normalFollowersCount
     */
    private Integer normalFollowersCount;
    /**
     * notifications
     */
    private Boolean notifications;
    /**
     * pinnedTweetIdsStr
     */
    private List<String> pinnedTweetIdsStr;
    /**
     * profileBannerExtensions
     */
    private ProfileBannerExtensionsX profileBannerExtensions;
    /**
     * profileBannerUrl
     */
    private String profileBannerUrl;
    /**
     * profileImageExtensions
     */
    private ProfileImageExtensionsX profileImageExtensions;
    /**
     * profileImageUrlHttps
     */
    private String profileImageUrlHttps;
    /**
     * profileInterstitialType
     */
    private String profileInterstitialType;
    /**
     * protectedX
     */
    private Boolean protectedX;
    /**
     * screenName
     */
    private String screenName;
    /**
     * statusesCount
     */
    private Integer statusesCount;
    /**
     * translatorType
     */
    private String translatorType;
    /**
     * url
     */
    private String url;
    /**
     * verified
     */
    private Boolean verified;
    /**
     * wantRetweets
     */
    private Boolean wantRetweets;
    /**
     * withheldInCountries
     */
    private List<?> withheldInCountries;
}
