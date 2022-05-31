package com.yike.apis.utils.twtter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.yike.apis.utils.twtter.vo.searchAdaptive.SearchAdaptive;
import com.yike.apis.utils.twtter.vo.userByScreenName.UserByScreenName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwtterUtil {

    public static Map searchAdaptive(String url, String authorization, String cookie, String xCsrfToken, String q, String count){
        String str = HttpRequest.get(String.format(url,q,count))
                .header("authorization",authorization)
                .header("cookie",cookie)
                .header("x-csrf-token",xCsrfToken)
                .execute().body();
        Map map = new HashMap();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            map = JSONUtil.toBean(str,Map.class);
            if(ObjectUtil.isNotEmpty(map)){
                flang = true;
            }
        }
        return map;
    }

    public static UserByScreenName userByScreenName(String url, String authorization, String cookie, String xCsrfToken, String screenName){
        String str = HttpRequest.get(url.replaceAll("&&&&&&&&",screenName))
                .header("authorization",authorization)
                .header("cookie",cookie)
                .header("x-csrf-token",xCsrfToken)
                .execute().body();
        UserByScreenName userByScreenName = new UserByScreenName();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            userByScreenName = JSONUtil.toBean(str,UserByScreenName.class);
            if(ObjectUtil.isNotEmpty(userByScreenName)){
                flang = true;
            }
        }
        return userByScreenName;
    }

    public static void main(String[] args) {
        Map map = searchAdaptive(TwtterApi.searchAdaptive.getUrl(), TwtterApi.searchAdaptive.getAuthorization(), TwtterApi.searchAdaptive.getCookie(), TwtterApi.searchAdaptive.getxCsrfToken(),"decentraland","20");
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        List list = new ArrayList();
        List list1 = new ArrayList();
        map = (Map) map.get("globalObjects");
        map1 = (Map) map.get("tweets");
        map2 = (Map) map.get("users");
        System.out.println(map2.keySet());
        if(ObjectUtil.isNotEmpty(map1) && map1.keySet().size() > 0){
            for(Object o:map1.keySet()){
                Map s = (Map) map1.get(o);
                list.add(map1.get(o));
                System.out.println(s.get("user_id_str"));
                list1.add(map2.get(s.get("user_id_str")));
                System.out.println(map2.get(s.get("user_id_str")));
            }
        }
        map3.put("tweets",list);
        map3.put("users",list1);
        String s = JSONObject.toJSONString(map3);
        SearchAdaptive searchAdaptive = JSONUtil.toBean(s,SearchAdaptive.class);
//        System.out.println(map1.keySet());
//        System.out.println(map2.keySet());
//        System.out.println(searchAdaptive);

//        UserByScreenName userByScreenName = userByScreenName(TwtterApi.userByScreenName.getUrl(), TwtterApi.userByScreenName.getAuthorization(), TwtterApi.userByScreenName.getCookie(), TwtterApi.userByScreenName.getxCsrfToken(),"aki_039");
//        System.out.println(userByScreenName);
    }
}
