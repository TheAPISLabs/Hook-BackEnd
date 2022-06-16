package com.yike.apis.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yike.apis.service.PosterService;
import com.yike.apis.utils.Coinmarketcap.CmcUtil;
import com.yike.apis.utils.Coinmarketcap.vo.defi.CmcDate;
import com.yike.apis.utils.Coinmarketcap.vo.defi.CryptoCurrencyList;
import com.yike.apis.utils.feixiaohao.FeiXiaoHaoUtil;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import com.yike.apis.utils.tokenView.TokenUtil;
import com.yike.apis.utils.tokenView.vo.Websearch.tokenEth.TokenEth;
import com.yike.apis.utils.tokenView.vo.Websearch.tokenbalance.Data;
import com.yike.apis.utils.tokenView.vo.Websearch.tokenbalance.TokenBalance;
import com.yike.apis.utils.tokenView.vo.Websearch.tokentrans.Tokentrans;
import com.yike.apis.utils.tokenView.vo.charts.Charts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PosterServiceImpl implements PosterService {
    @Autowired
    private RedisTemplate redisTemplate;
//    @Override
//    public ResponseData getPoster(String address) {
//        TokenBalance tokenBalance = TokenUtil.tokenbalance(address);
//        if(ObjectUtil.isNotEmpty(tokenBalance) && ObjectUtil.isNotEmpty(tokenBalance.getData())){
//            List<Data> datas = tokenBalance.getData().stream().filter(s -> ObjectUtil.isEmpty(s.getTokenInfo().getC()) && Double.parseDouble(s.getBalance()) > 0D).collect(Collectors.toList());
//            if(ObjectUtil.isNotEmpty(redisTemplate.opsForValue().get("cmcDefiDate"))){
//                Long time = (1000L * 60L * 60L * 24L * 90L + System.currentTimeMillis()) / 1000L;
//                CmcDate cmcDate = (CmcDate) redisTemplate.opsForValue().get("cmcDefiDate");
//                List<CryptoCurrencyList> cryptoCurrencyList = cmcDate.getData().getCryptoCurrencyList();
//                Data dataa = new Data();
//                BigDecimal b = new BigDecimal(0);
//                Map map = new HashMap();
//                List<String> list = new ArrayList<>();
//                for(Data data : datas){
//                    List<CryptoCurrencyList> cryptoCurrencyList1 = cryptoCurrencyList.stream().filter(s -> s.getName().equalsIgnoreCase(data.getTokenInfo().getS())).collect(Collectors.toList());
//                    if(ObjectUtil.isNotEmpty(cryptoCurrencyList1)){
//                        CryptoCurrencyList currencyList = cryptoCurrencyList1.get(0);
//                        map = CmcUtil.chart(currencyList.getId().toString());
//                        if(ObjectUtil.isNotEmpty(map)){
//                            map = (Map) map.get("data");
//                            if(ObjectUtil.isNotEmpty(map)){
//                                map = (Map) map.get("points");
//                                Set<String> set = map.keySet();
//                                list = new ArrayList<>(set);
//                                Map map1 = (Map) map.get(list.get(list.size() - 1));
//                                JSONArray jsonArray = (JSONArray) map1.get("v");
//                                BigDecimal price = new BigDecimal(jsonArray.get(0).toString());
//                                BigDecimal c = new BigDecimal(data.getBalance()).divide(BigDecimal.TEN.pow(Integer.parseInt(data.getTokenInfo().getD()))).multiply(price);
//                                if(c.compareTo(b) > 0){
//                                    dataa = data;
//                                    b = c;
//                                }
//                            }
//                        }
//                    }
//                }
//                if(ObjectUtil.isNotEmpty(dataa) && ObjectUtil.isNotEmpty(dataa.getBalance())){
//                    Integer limit = 50;
//                    for(Integer start = 1;start <= dataa.getTransferCnt() / limit;start++){
//                        Tokentrans tokentrans = TokenUtil.tokentrans(address,dataa.getHash(),start.toString(),limit.toString());
//                        if(ObjectUtil.isNotEmpty(tokentrans) && ObjectUtil.isNotEmpty(tokentrans.getData()) && !tokentrans.getCode().equals(10001)){
//                            for(com.yike.apis.utils.tokenView.vo.Websearch.tokentrans.Data data : tokentrans.getData()){
//                                if(time <= data.getTime()){
//                                    time = data.getTime();
//                                }
//                            }
//                        }
//                    }
//                    List<Long> timeList = list.stream()
//                            .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
//                    Long h = getSimilarNumber(time,timeList,false);
//                    Map map1 = (Map) map.get(h.toString());
//                    JSONArray jsonArray = (JSONArray) map1.get("v");
//                    BigDecimal price = new BigDecimal(jsonArray.get(0).toString());
//                    BigDecimal c = new BigDecimal(dataa.getBalance()).multiply(price);
//                    BigDecimal yield = b.subtract(c).divide(c,6,BigDecimal.ROUND_DOWN);
//                    TokenEth tokenEth = TokenUtil.tokenEth(dataa.getHash());
//                    Integer holderCnt = 0;
//                    if(ObjectUtil.isNotEmpty(tokenEth) && tokenEth.getCode().equals(1)){
//                        holderCnt = tokenEth.getData().getHolderCnt();
//                    }
//                    Map map2 = new HashMap();
//                    map2.put("yield",yield);
//                    map2.put("worth",b.toPlainString());
//                    map2.put("holderCnt",holderCnt);
//                    return ResponseDataUtil.buildSuccess(map2);
//                }
//
//
//            }
//
//        }
//        Map map2 = new HashMap();
//        map2.put("yield",0);
//        map2.put("worth",0);
//        map2.put("holderCnt",0);
//        return ResponseDataUtil.buildSuccess(map2);
//    }

    @Override
    public ResponseData getPoster(String address) {
        TokenBalance tokenBalance = TokenUtil.tokenbalance(address);
        if(ObjectUtil.isNotEmpty(tokenBalance) && ObjectUtil.isNotEmpty(tokenBalance.getData())){
            List<Data> datas = tokenBalance.getData().stream().filter(s -> ObjectUtil.isEmpty(s.getTokenInfo().getC()) && new BigDecimal(s.getBalance()).divide(BigDecimal.TEN.pow(Integer.parseInt(s.getTokenInfo().getD())),4,BigDecimal.ROUND_DOWN).compareTo(new BigDecimal(0)) > 0).collect(Collectors.toList());
            if(datas.size() > 0){
                Long time = 1000L * 60L * 60L * 24L * 90L + System.currentTimeMillis();
                Data dataa = new Data();
                BigDecimal b = new BigDecimal(0);
                List<List> list = new ArrayList<>();
                for(Data data : datas){
                    Charts charts = FeiXiaoHaoUtil.chartsV2(data.getTokenInfo().getS(),data.getTokenInfo().getF());
                    if(ObjectUtil.isNotEmpty(charts)){
                        String value = charts.getValue();
                        value = "["+value+"]";
                        JSONArray jsonArray = JSONUtil.parseArray(value);
                        list = JSONUtil.toList(jsonArray,List.class);
                        if(ObjectUtil.isNotEmpty(list) && list.size() > 0){
                            List list1 = list.get(list.size() - 1);
                            BigDecimal price = new BigDecimal(list1.get(1).toString());
                            BigDecimal c = new BigDecimal(data.getBalance()).divide(BigDecimal.TEN.pow(Integer.parseInt(data.getTokenInfo().getD()))).multiply(price);
                            if(c.compareTo(b) > 0){
                                dataa = data;
                                b = c;
                            }
                        }
                    }
                }

                if(ObjectUtil.isNotEmpty(dataa) && ObjectUtil.isNotEmpty(dataa.getBalance())){
                    Integer limit = 50;
                    for(Integer start = 1;start <= dataa.getTransferCnt() / limit;start++){
                        Tokentrans tokentrans = TokenUtil.tokentrans(address,dataa.getHash(),start.toString(),limit.toString());
                        if(ObjectUtil.isNotEmpty(tokentrans) && ObjectUtil.isNotEmpty(tokentrans.getData()) && !tokentrans.getCode().equals(10001)){
                            for(com.yike.apis.utils.tokenView.vo.Websearch.tokentrans.Data data : tokentrans.getData()){
                                if(time <= data.getTime()){
                                    time = data.getTime();
                                }
                            }
                        }
                    }
                    List<Object> stringList = list.stream().map(s -> s.get(0)).collect(Collectors.toList());
                    List<Long> timeList = stringList.stream()
                            .map(s -> Long.parseLong(s.toString())).collect(Collectors.toList());
                    Integer h = getSimilarNumber2(time,timeList,false);
                    list = list.subList(0,h);
                    List<Object> list1 = list.stream().map(s -> s.get(1)).collect(Collectors.toList());
                    List<String> strings = list1.stream().map(s -> String.valueOf(s.toString())).collect(Collectors.toList());
                    String max = Collections.max(strings);
                    BigDecimal price = new BigDecimal(max);
                    BigDecimal c = new BigDecimal(dataa.getBalance()).multiply(price);
                    BigDecimal yield = b.subtract(c).divide(c,6,BigDecimal.ROUND_DOWN);
                    TokenEth tokenEth = TokenUtil.tokenEth(dataa.getHash());
                    Integer holderCnt = 0;
                    if(ObjectUtil.isNotEmpty(tokenEth) && tokenEth.getCode().equals(1)){
                        holderCnt = tokenEth.getData().getHolderCnt();
                    }
                    Map map2 = new HashMap();
                    map2.put("yield",yield);
                    map2.put("worth",b.toPlainString());
                    map2.put("holderCnt",holderCnt);
                    return ResponseDataUtil.buildSuccess(map2);
                }
            }

        }
        Map map2 = new HashMap();
        map2.put("yield",0);
        map2.put("worth",0);
        map2.put("holderCnt",0);
        return ResponseDataUtil.buildSuccess(map2);
    }

    /**
     * 获取集合中最接近的数
     * @param number    需要查找的数字
     * @param numbers   数字集合
     * @param flag   如果有两个相近的数据   true:选择大数  false:选择小数
     * @return  相近结果
     */
    public Long getSimilarNumber (Long number, List<Long> numbers, Boolean flag)
    {
        if (null == numbers || numbers.isEmpty())
        {
            throw new RuntimeException("数字集合不能为空");
        }
        // 如果集合包含需要查找的数字,直接返回
        if (numbers.contains(number))
        {
            return number;
        }
        // 把需要查找的数字添加到集合中
        numbers.add(number);
        // 对集合进行排序,转换成有序集合
        numbers.sort(Comparator.comparing(Long::valueOf));
        List<Long> numList = numbers;
        // 获取集合的长度
        int size = numList.size();
        // 获取需要查找的数字所在位置
        int index = numList.indexOf(number);
        // 如果所在位置为最前面,表示第一位及为临近数
        if (index <= 0)
        {
            return numList.get(1);
        }
        // 如果所在位置为最后一位,表示在它前面的一位为临近数
        if (index >= size - 1)
        {
            return numList.get(size - 2);
        }
        // 前一个数据
        Long before = numList.get(index - 1);
        // 后一个数据
        Long after = numList.get(index + 1);
        // 需要查找的数字和前一个数字相差值
        double beforeDifference = number.doubleValue() - before.doubleValue();
        // 需要查找的数字和后一个数字相差值
        double afterDifference = after.doubleValue() - number.doubleValue();
        // 如果两个值相同
        if (beforeDifference == afterDifference)
        {
            // 判断取大值,还是取小值
            return flag ? after : before;
        }
        // 取最相近的值
        return beforeDifference < afterDifference ? before : after;
    }

    /**
     * 获取集合中最接近的数
     * @param number    需要查找的数字
     * @param numbers   数字集合
     * @param flag   如果有两个相近的数据   true:选择大数  false:选择小数
     * @return  下标
     */
    public Integer getSimilarNumber2(Long number, List<Long> numbers, Boolean flag)
    {
        if (null == numbers || numbers.isEmpty())
        {
            throw new RuntimeException("数字集合不能为空");
        }
        // 把需要查找的数字添加到集合中
        numbers.add(number);
        // 对集合进行排序,转换成有序集合
        numbers.sort(Comparator.comparing(Long::valueOf));
        List<Long> numList = numbers;
        // 获取集合的长度
        int size = numList.size();
        // 获取需要查找的数字所在位置
        int index = numList.indexOf(number);
        // 如果所在位置为最前面,表示第一位及为临近数
        if (index <= 0)
        {
            return 0;
        }
        // 如果所在位置为最后一位,表示在它前面的一位为临近数
        if (index >= size - 1)
        {
            return size - 2;
        }
        // 前一个数据
        Long before = numList.get(index - 1);
        // 后一个数据
        Long after = numList.get(index + 1);
        // 需要查找的数字和前一个数字相差值
        double beforeDifference = number.doubleValue() - before.doubleValue();
        // 需要查找的数字和后一个数字相差值
        double afterDifference = after.doubleValue() - number.doubleValue();
        // 如果两个值相同
        if (beforeDifference == afterDifference)
        {
            // 判断取大值,还是取小值
            return flag ? index - 1 : index + 1;
        }
        // 取最相近的值
        return beforeDifference < afterDifference ? index + 1 : index - 1;
    }


    public static void main(String[] args) {
        Map map = CmcUtil.chart("4943");
        if(ObjectUtil.isNotEmpty(map)){
            map = (Map) map.get("data");
            if(ObjectUtil.isNotEmpty(map)){
                map = (Map) map.get("points");
                Set<Long> set = map.keySet();
                List<Long> list = new ArrayList<>(set);
                Map map1 = (Map) map.get(list.get(list.size() - 1));
                JSONArray jsonArray = (JSONArray) map1.get("v");
                BigDecimal price = new BigDecimal(jsonArray.get(0).toString());
                System.out.println(price.setScale(8));
            }
        }
    }

    public static String getType(Object obj) {
        /**
         *  1. 通过反射获取传来参数的JavaClass对象
         *  2. 获取到JavaClass对象的类型名称
         *  3. 将参数的类型名称返回
         */
        return obj.getClass().getTypeName();
    }

}
