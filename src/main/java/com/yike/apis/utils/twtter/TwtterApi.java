package com.yike.apis.utils.twtter;

public enum TwtterApi {
    searchAdaptive("https://twitter.com/i/api/2/search/adaptive.json?include_profile_interstitial_type=1&include_blocking=1&include_blocked_by=1&include_followed_by=1&include_want_retweets=1&include_mute_edge=1&include_can_dm=1&include_can_media_tag=1&include_ext_has_nft_avatar=1&skip_status=1&cards_platform=Web-12&include_cards=1&include_ext_alt_text=true&include_quote_count=true&include_reply_count=1&tweet_mode=extended&include_entities=true&include_user_entities=true&include_ext_media_color=true&include_ext_media_availability=true&include_ext_sensitive_media_warning=true&include_ext_trusted_friends_metadata=true&send_error_codes=true&simple_quoted_tweet=true&q=%s&count=%s&query_source=typed_query&pc=1&spelling_corrections=1&ext=mediaStats,highlightedLabel,hasNftAvatar,voiceInfo,enrichments,superFollowMetadata,unmentionInfo",
            "Bearer AAAAAAAAAAAAAAAAAAAAANRILgAAAAAAnNwIzUejRCOuH5E6I8xnZz4puTs%3D1Zv7ttfk8LF81IUq16cHjhLTvJu4FA33AGWWjCpTnA",
            "guest_id_marketing=v1%3A164862570603992407; guest_id_ads=v1%3A164862570603992407; kdt=NR5GBYGrCqtF8RlBU69OUamX2aP3XXbGY9b656Ns; des_opt_in=Y; _gcl_au=1.1.1626507324.1648710405; personalization_id=\"v1_y+el3j+U2v3nuudykQWtZA==\"; guest_id=v1%3A164990163787435357; g_state={\"i_l\":0}; auth_token=8066854d3e0045e380917114402a127f122ed138; ct0=473ef4d26a4c1f326670f2dc9a81dbc5d429888e7e9de986b7e350fabc3c2d8800b1c30926466525082837a9fe0ce1d3be45f2137a93654a028b36943f11685453be81c8ac101ed386746736e5192f77; twid=u%3D1327570883523743744; mbox=PC#b983c2de09da420d80b6177d00e2e1c4.38_0#1713236599|session#304d1ac57bbd406aa27b479064b47043#1649993659; _ga_34PHSZMC42=GS1.1.1649991599.12.1.1649991822.0; _ga=GA1.2.1058231630.1648625701; external_referer=padhuUp37zjCLSb14MwXkpe99Hg22XqwLyBOZVjmJjU%3D|0|8e8t2xd8A2w%3D; _gid=GA1.2.918365272.1650785268",
            "473ef4d26a4c1f326670f2dc9a81dbc5d429888e7e9de986b7e350fabc3c2d8800b1c30926466525082837a9fe0ce1d3be45f2137a93654a028b36943f11685453be81c8ac101ed386746736e5192f77"),

    userByScreenName("https://twitter.com/i/api/graphql/Bhlf1dYJ3bYCKmLfeEQ31A/UserByScreenName?variables=%7B%22screen_name%22%3A%22&&&&&&&&%22%2C%22withSafetyModeUserFields%22%3Atrue%2C%22withSuperFollowsUserFields%22%3Atrue%7D&=%7B%22screen_name%22%3A%22hatakenjiro%22%2C%22withSafetyModeUserFields%22%3Atrue%2C%22withSuperFollowsUserFields%22%3Atrue%7D",
                           "Bearer AAAAAAAAAAAAAAAAAAAAANRILgAAAAAAnNwIzUejRCOuH5E6I8xnZz4puTs%3D1Zv7ttfk8LF81IUq16cHjhLTvJu4FA33AGWWjCpTnA",
                           "guest_id_marketing=v1%3A164862570603992407; guest_id_ads=v1%3A164862570603992407; kdt=NR5GBYGrCqtF8RlBU69OUamX2aP3XXbGY9b656Ns; des_opt_in=Y; _gcl_au=1.1.1626507324.1648710405; personalization_id=\"v1_y+el3j+U2v3nuudykQWtZA==\"; guest_id=v1%3A164990163787435357; g_state={\"i_l\":0}; auth_token=8066854d3e0045e380917114402a127f122ed138; ct0=473ef4d26a4c1f326670f2dc9a81dbc5d429888e7e9de986b7e350fabc3c2d8800b1c30926466525082837a9fe0ce1d3be45f2137a93654a028b36943f11685453be81c8ac101ed386746736e5192f77; twid=u%3D1327570883523743744; mbox=PC#b983c2de09da420d80b6177d00e2e1c4.38_0#1713236599|session#304d1ac57bbd406aa27b479064b47043#1649993659; _ga_34PHSZMC42=GS1.1.1649991599.12.1.1649991822.0; _ga=GA1.2.1058231630.1648625701; external_referer=padhuUp37zjCLSb14MwXkpe99Hg22XqwLyBOZVjmJjU%3D|0|8e8t2xd8A2w%3D; _gid=GA1.2.918365272.1650785268",
                           "473ef4d26a4c1f326670f2dc9a81dbc5d429888e7e9de986b7e350fabc3c2d8800b1c30926466525082837a9fe0ce1d3be45f2137a93654a028b36943f11685453be81c8ac101ed386746736e5192f77");

    private String url;
    private String authorization;
    private String cookie;
    private String xCsrfToken;

    TwtterApi(String url, String authorization, String cookie, String xCsrfToken) {
        this.url = url;
        this.authorization = authorization;
        this.cookie = cookie;
        this.xCsrfToken = xCsrfToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getxCsrfToken() {
        return xCsrfToken;
    }

    public void setxCsrfToken(String xCsrfToken) {
        this.xCsrfToken = xCsrfToken;
    }
}
