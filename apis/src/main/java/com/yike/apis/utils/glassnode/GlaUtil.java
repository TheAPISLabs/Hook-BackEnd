package com.yike.apis.utils.glassnode;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.yike.apis.utils.glassnode.vo.GlaData;

import java.util.ArrayList;
import java.util.List;

public class GlaUtil {
    public static List<GlaData> getGlassnode(String url,String a,String cookie){
        String str = HttpRequest.get(String.format(url,a))
                .header("cookie",cookie)
                .execute().body();
        List<GlaData> list = new ArrayList();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            list = JSONUtil.toList(JSONUtil.parseArray(str),GlaData.class);
            if(ObjectUtil.isNotEmpty(list)){
                flang = true;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(getGlassnode(Glassnode.transactions_exchanges_inflow_volume_number.getUrl(),"BTC",Glassnode.transactions_exchanges_inflow_volume_number.getCookie()));
    }
}
