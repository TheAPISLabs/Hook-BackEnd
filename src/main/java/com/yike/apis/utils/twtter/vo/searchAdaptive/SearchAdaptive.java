package com.yike.apis.utils.twtter.vo.searchAdaptive;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class SearchAdaptive {


    /**
     * tweets
     */
    private List<Tweets> tweets;
    /**
     * users
     */
    private List<Users> users;
}
