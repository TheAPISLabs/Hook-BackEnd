package com.yike.apis.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.http.HttpRequest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {
    public static List<Integer[]> twoSum(Integer[] nums, Integer target) {
        List<Integer[]> list = new ArrayList();
        Integer[] res = new Integer[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(Integer i=0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                res[0]=map.get(nums[i]);
                res[1]=i;
                list.add(res);
            }
            map.put(target-nums[i], i);
        }
        return list;
    }

    public static void main(String[] args) throws ParseException {
//        Integer[] nums = {1,3,5,7,9,2,4,6,8};
//        Integer target = 9;
//        List<Integer[]> list = new ArrayList<>();
//        list = twoSum(nums,target);
//        System.out.println(list);
//        String json = "{\n" +
//                "    \"method\": \"qn_fetchNFTsByCreator\",\n" +
//                "    \"id\": 12,\n" +
//                "    \"params\": [\n" +
//                "      {\n" +
//                "        \"creator\": \"DznU28LgherhU2JwC2db3KmAeWPqoF9Yx2aVtNUudW6R\",\n" +
//                "        \"page\": 1,\n" +
//                "        \"perPage\": 3\n" +
//                "      }\n" +
//                "    ]\n" +
//                "  }";
//        String str = HttpRequest.post("http://sample-endpoint-name.network.quiknode.pro/token-goes-here/")
//                .body(json)
//                .execute().body();
        //随机生成密钥
        
//        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
//        System.out.println(IdUtil.simpleUUID());
        String s = "[UserVo(uId=b6a5264ac8b448a3a7f8f4388c6de31c, userIcon=https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fbao%2Fuploaded%2Fi1%2F88278447%2FO1CN01XKAlVD2CGmFQQ1qJr_%21%2188278447.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1656230268&t=0ac928b), UserVo(uId=b6a5264ac8b448a3a7f8f4388c6de31c, userIcon=https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.alicdn.com%2Fbao%2Fuploaded%2Fi1%2F88278447%2FO1CN01XKAlVD2CGmFQQ1qJr_%21%2188278447.jpg&refer=http%3A%2F%2Fimg.alicdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1656230268&t=0ac928b)]";
        System.out.println(s.indexOf("b6a5264ac8b448a3a7f8f4388c6de31c"));
    }
}
