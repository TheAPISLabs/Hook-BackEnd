package com.yike.apis.utils.twtter.vo.userByScreenName;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result
 */
@NoArgsConstructor
@Data
public class Result {
    /**
     * typename
     */
    private String typename;
    /**
     * id
     */
    private String id;
    /**
     * restId
     */
    private String restId;
    /**
     * affiliatesHighlightedLabel
     */
    private AffiliatesHighlightedLabel affiliatesHighlightedLabel;
    /**
     * hasNftAvatar
     */
    private Boolean hasNftAvatar;
    /**
     * legacy
     */
    private Legacy legacy;
    /**
     * smartBlockedBy
     */
    private Boolean smartBlockedBy;
    /**
     * smartBlocking
     */
    private Boolean smartBlocking;
    /**
     * superFollowEligible
     */
    private Boolean superFollowEligible;
    /**
     * superFollowedBy
     */
    private Boolean superFollowedBy;
    /**
     * superFollowing
     */
    private Boolean superFollowing;
    /**
     * legacyExtendedProfile
     */
    private LegacyExtendedProfile legacyExtendedProfile;
    /**
     * isProfileTranslatable
     */
    private Boolean isProfileTranslatable;
}
