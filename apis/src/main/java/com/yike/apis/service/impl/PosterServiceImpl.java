package com.yike.apis.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.yike.apis.service.PosterService;
import com.yike.apis.utils.Coinmarketcap.CmcUtil;
import com.yike.apis.utils.Coinmarketcap.vo.defi.CmcDate;
import com.yike.apis.utils.Coinmarketcap.vo.defi.CryptoCurrencyList;
import com.yike.apis.utils.feixiaohao.FeiXiaoHaoUtil;
import com.yike.apis.utils.feixiaohao.vo.holders.Holders;
import com.yike.apis.utils.reponseUtil.ResponseData;
import com.yike.apis.utils.reponseUtil.ResponseDataUtil;
import com.yike.apis.utils.tokenView.TokenUtil;
import com.yike.apis.utils.tokenView.vo.Websearch.tokenEth.TokenEth;
import com.yike.apis.utils.tokenView.vo.Websearch.tokenbalance.Data;
import com.yike.apis.utils.tokenView.vo.Websearch.tokenbalance.TokenBalance;
import com.yike.apis.utils.tokenView.vo.Websearch.tokentrans.Tokentrans;
import com.yike.apis.utils.tokenView.vo.address.Address;
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

    @Override
    public ResponseData getPoster(String address) {
        TokenBalance tokenBalance = TokenUtil.tokenbalance(address);
        if(ObjectUtil.isNotEmpty(tokenBalance) && ObjectUtil.isNotEmpty(tokenBalance.getData())){
            List<Data> datas = tokenBalance.getData().stream().filter(s -> ObjectUtil.isEmpty(s.getTokenInfo().getC()) && new BigDecimal(s.getBalance()).divide(BigDecimal.TEN.pow(Integer.parseInt(s.getTokenInfo().getD())),3,BigDecimal.ROUND_DOWN).compareTo(new BigDecimal(0.01)) > 0).collect(Collectors.toList());
            if(datas.size() > 0){
                Long time = 1000L * 60L * 60L * 24L * 90L + System.currentTimeMillis();
                Data dataa = new Data();
                BigDecimal b = new BigDecimal(0);
                List<List> list = new ArrayList<>();
                int i = 0;
                for(Data data : datas){
                    i++;
                    if(i > 20){
                        break;
                    }
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
        Address address1 = TokenUtil.address(address);
        Map map2 = new HashMap();
        if(ObjectUtil.isNotEmpty(address1) && address1.getCode().equals(1)){
            Charts charts = FeiXiaoHaoUtil.charts("ethereum");
            String value = charts.getValue();
            value = "["+value+"]";
            JSONArray jsonArray = JSONUtil.parseArray(value);
            List<List> list = JSONUtil.toList(jsonArray,List.class);
            List<Object> stringList = list.stream().map(s -> s.get(0)).collect(Collectors.toList());
            List<Long> timeList = stringList.stream()
                    .map(s -> Long.parseLong(s.toString())).collect(Collectors.toList());
            Integer h = getSimilarNumber2(address1.getData().getTxs().get(address1.getData().getTxs().size() - 1).getTime() * 1000L,timeList,false);
            list = list.subList(0,h+1);
            List<Object> list1 = list.stream().map(s -> s.get(1)).collect(Collectors.toList());
            List<String> strings = list1.stream().map(s -> String.valueOf(s.toString())).collect(Collectors.toList());
            String max = Collections.max(strings);
            BigDecimal price = new BigDecimal(max);
            List list2 = list.get(list.size() - 1);
            BigDecimal newPrice = new BigDecimal(list2.get(1).toString());
            Long addrcount = 0L;
            Holders holders = FeiXiaoHaoUtil.holders("ethereum");
            if(ObjectUtil.isNotEmpty(holders) && holders.getCode().equals(200)){
                if(ObjectUtil.isNotEmpty(holders.getData()) && ObjectUtil.isNotEmpty(holders.getData().getTop())){
                    addrcount = holders.getData().getTop().getAddrcount();
                }
            }
            map2.put("yield",newPrice.subtract(price).divide(price,6,BigDecimal.ROUND_DOWN));
            map2.put("worth",new BigDecimal(address1.getData().getBalance()).multiply(newPrice).setScale(6,BigDecimal.ROUND_DOWN));
            map2.put("holderCnt",addrcount);
        }else {
            map2.put("yield",0);
            map2.put("worth",0);
            map2.put("holderCnt",0);
        }
        return ResponseDataUtil.buildSuccess(map2);
    }

    /**
     * Gets the closest number in the set
     * @param number    The number to look up
     * @param numbers   Digital collection
     * @param flag   If there are two similar values true: select a large number false: select a decimal number
     * @return  Similar results
     */
    public Long getSimilarNumber (Long number, List<Long> numbers, Boolean flag)
    {
        if (null == numbers || numbers.isEmpty())
        {
            throw new RuntimeException("The set of numbers cannot be empty");
        }
        // If the collection contains a number to look up, return it directly
        if (numbers.contains(number))
        {
            return number;
        }
        // Adds the number you want to find to the collection
        numbers.add(number);
        // Sort the set and convert it to an ordered set
        numbers.sort(Comparator.comparing(Long::valueOf));
        List<Long> numList = numbers;
        // Gets the length of the collection
        int size = numList.size();
        // Gets the location of the number to be searched
        int index = numList.indexOf(number);
        // If the position is the first, it means that the first and adjacent numbers
        if (index <= 0)
        {
            return numList.get(1);
        }
        // If the position is the last digit, it means that the digit before it is adjacent
        if (index >= size - 1)
        {
            return numList.get(size - 2);
        }
        Long before = numList.get(index - 1);
        Long after = numList.get(index + 1);
        double beforeDifference = number.doubleValue() - before.doubleValue();
        double afterDifference = after.doubleValue() - number.doubleValue();
        if (beforeDifference == afterDifference)
        {
            return flag ? after : before;
        }
        return beforeDifference < afterDifference ? before : after;
    }

    public static Integer getSimilarNumber2(Long number, List<Long> numbers, Boolean flag)
    {
        if (null == numbers || numbers.isEmpty())
        {
            throw new RuntimeException("The set of numbers cannot be empty");
        }
        numbers.add(number);
        numbers.sort(Comparator.comparing(Long::valueOf));
        List<Long> numList = numbers;
        int size = numList.size();
        int index = numList.indexOf(number);
        if (index <= 0)
        {
            return 0;
        }
        if (index >= size - 1)
        {
            return size - 2;
        }
        Long before = numList.get(index - 1);
        Long after = numList.get(index + 1);
        double beforeDifference = number.doubleValue() - before.doubleValue();
        double afterDifference = after.doubleValue() - number.doubleValue();
        if (beforeDifference == afterDifference)
        {
            return flag ? index - 1 : index + 1;
        }
        return beforeDifference < afterDifference ? index + 1 : index - 1;
    }


    public static String getType(Object obj) {
        return obj.getClass().getTypeName();
    }

    public static void main(String[] args) {
        Address address1 = TokenUtil.address("0xa29e6877d85afc6bb870a818a61414bbba8006fb");
        Map map2 = new HashMap();
        if(ObjectUtil.isNotEmpty(address1) && address1.getCode().equals(1)){
            Charts charts = FeiXiaoHaoUtil.charts("ethereum");
            String value = charts.getValue();
            value = "["+value+"]";
            JSONArray jsonArray = JSONUtil.parseArray(value);
            List<List> list = JSONUtil.toList(jsonArray,List.class);
            List<Object> stringList = list.stream().map(s -> s.get(0)).collect(Collectors.toList());
            List<Long> timeList = stringList.stream()
                    .map(s -> Long.parseLong(s.toString())).collect(Collectors.toList());
            Integer h = getSimilarNumber2(address1.getData().getTxs().get(address1.getData().getTxs().size() - 1).getTime() * 1000L,timeList,false);
            list = list.subList(0,h);
            List<Object> list1 = list.stream().map(s -> s.get(1)).collect(Collectors.toList());
            List<String> strings = list1.stream().map(s -> String.valueOf(s.toString())).collect(Collectors.toList());
            String max = Collections.max(strings);
            BigDecimal price = new BigDecimal(max);
            List list2 = list.get(list.size() - 1);
            BigDecimal newPrice = new BigDecimal(list2.get(1).toString());
            map2.put("yield",newPrice.subtract(price).divide(price,6,BigDecimal.ROUND_DOWN));
            map2.put("worth",new BigDecimal(address1.getData().getBalance()).multiply(newPrice).setScale(6,BigDecimal.ROUND_DOWN));
            map2.put("holderCnt",ObjectUtil.isEmpty(address1.getData().getRank()) ? 0 : address1.getData().getRank());
        }else {
            map2.put("yield",0);
            map2.put("worth",0);
            map2.put("holderCnt",0);
        }
        System.out.println(map2);
    }
}
