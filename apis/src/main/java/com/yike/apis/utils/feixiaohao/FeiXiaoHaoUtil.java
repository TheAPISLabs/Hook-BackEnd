package com.yike.apis.utils.feixiaohao;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yike.apis.utils.tokenView.vo.Websearch.Coinlist;
import com.yike.apis.utils.tokenView.vo.Websearch.Websearch;
import com.yike.apis.utils.tokenView.vo.charts.Charts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeiXiaoHaoUtil {
    public static Websearch websearch(String code){
        String str = HttpUtil.get(String.format(FeiXiaoHaoApi.websearch,code.toUpperCase()));
        Websearch websearch = new Websearch();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            websearch = JSONUtil.toBean(str,Websearch.class);
            if(ObjectUtil.isNotEmpty(websearch)){
                flang = true;
            }
        }
        return websearch;
    }

    public static Charts charts(String code){
        String str = HttpUtil.get(String.format(FeiXiaoHaoApi.charts,code.toLowerCase()));
        Charts charts = new Charts();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            charts = JSONUtil.toBean(str,Charts.class);
            if(ObjectUtil.isNotEmpty(charts)){
                flang = true;
            }
        }
        return charts;
    }

    public static Charts chartsV2(String code,String name){
        Websearch websearch = websearch(code);
        if(ObjectUtil.isNotEmpty(websearch) && websearch.getCode().equals(200)){
            List<Coinlist> coinlist = websearch.getCoinlist();
            coinlist = coinlist.stream().filter(s -> s.getSymbol().equals(code) && name.toLowerCase().indexOf(s.getCoinname().toLowerCase()) != -1).collect(Collectors.toList());
            if(coinlist.size() > 0){
                Coinlist coinlist1 = coinlist.get(0);
                String str = HttpUtil.get(String.format(FeiXiaoHaoApi.charts,coinlist1.getCoincode()));
                Charts charts = new Charts();
                Boolean flang = false;
                //Retry mechanism,3 times
                for(int i = 0;i < 4;i++){
                    if(flang){
                        break;
                    }
                    charts = JSONUtil.toBean(str,Charts.class);
                    if(ObjectUtil.isNotEmpty(charts)){
                        flang = true;
                    }
                }
                return charts;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Charts charts = chartsV2("AGI","SingularityNET Token");
        String value = charts.getValue();
        value = "["+value+"]";
        System.out.println(value);
        JSONArray jsonArray = JSONUtil.parseArray(value);
        List<List> list1 = JSONUtil.toList(jsonArray,List.class);
        System.out.println(list1.get(0).get(2));
    }
}
