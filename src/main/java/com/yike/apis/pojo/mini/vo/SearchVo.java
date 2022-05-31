package com.yike.apis.pojo.mini.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchVo {
    private Long rank;
    private Long heat;
    private String SearchName;

    public SearchVo(Long rank, Long heat, String searchName) {
        this.rank = rank;
        this.heat = heat;
        SearchName = searchName;
    }

}
