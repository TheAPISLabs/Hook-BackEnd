package com.yike.apis.utils.opensea;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.yike.apis.utils.opensea.vo.Collection;
import com.yike.apis.utils.twtter.vo.userByScreenName.UserByScreenName;

public class OpenseaUtil {

    public static Collection collection(String url, String X_API_KEY, String accept, String name){
        String str = HttpRequest.get(String.format(url,name))
                .header("X_API_KEY",X_API_KEY)
                .header("accept",accept)
                .execute().body();
        Collection collection = new Collection();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            collection = JSONUtil.toBean(str,Collection.class);
            if(ObjectUtil.isNotEmpty(collection)){
                flang = true;
            }
        }
        return collection;
    }
}
