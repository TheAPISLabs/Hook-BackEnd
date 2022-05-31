package com.yike.apis.utils.tokenView;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.yike.apis.utils.tokenView.vo.Maincoinex.Maincoinex;
import com.yike.apis.utils.tokenView.vo.TokenTokentrans;
import com.yike.apis.utils.tokenView.vo.contract.Contract;
import com.yike.apis.utils.tokenView.vo.normal.Normal;
import com.yike.apis.utils.tokenView.vo.token.Data;
import com.yike.apis.utils.tokenView.vo.token.Token;
import com.yike.apis.utils.tokenView.vo.tokenEth.TokenEth;
import com.yike.apis.utils.tokenView.vo.tokenbalance.TokenBalance;
import com.yike.apis.utils.tokenView.vo.tokentrans.Tokentrans;

import java.util.List;
import java.util.stream.Collectors;

public class TokenUtil {
    public static TokenBalance tokenbalance(String address){
        String str = HttpUtil.get(String.format(TokenViewApi.tokenbalance,address.toLowerCase()));
        TokenBalance tokenBalance = new TokenBalance();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            tokenBalance = JSONUtil.toBean(str,TokenBalance.class);
            if(ObjectUtil.isNotEmpty(tokenBalance)){
                flang = true;
            }
        }
        return tokenBalance;
    }

    public static TokenTokentrans tokenTokentrans(String address,String start, String limit){
        String str = HttpUtil.get(String.format(TokenViewApi.tokenTokentrans,address.toLowerCase(),start,limit));
        TokenTokentrans tokenTokentrans = new TokenTokentrans();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            tokenTokentrans = JSONUtil.toBean(str,TokenTokentrans.class);
            if(ObjectUtil.isNotEmpty(tokenTokentrans)){
                flang = true;
            }
        }
        return tokenTokentrans;
    }

    public static Tokentrans tokentrans(String address, String tokenAddress, String start, String limit){
        String str = HttpUtil.get(String.format(TokenViewApi.tokentrans,address.toLowerCase(),tokenAddress,start,limit));
        Tokentrans tokentrans = new Tokentrans();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            tokentrans = JSONUtil.toBean(str,Tokentrans.class);
            if(ObjectUtil.isNotEmpty(tokentrans)){
                flang = true;
            }
        }
        return tokentrans;
    }

    public static Token gettokens(){
        String str = HttpUtil.get(TokenViewApi.token);
        Token token = new Token();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            token = JSONUtil.toBean(str,Token.class);
            if(ObjectUtil.isNotEmpty(token)){
                flang = true;
            }
        }
        return token;
    }

    public static Maincoinex maincoinexchange(){
        String str = HttpUtil.get(TokenViewApi.maincoinexchange);
        Maincoinex maincoinex = new Maincoinex();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            maincoinex = JSONUtil.toBean(str,Maincoinex.class);
            if(ObjectUtil.isNotEmpty(maincoinex)){
                flang = true;
            }
        }
        return maincoinex;
    }

    public static Normal normal(String address, String start, String limit){
        String str = HttpUtil.get(String.format(TokenViewApi.normal,address.toLowerCase(),start,limit));
        Normal normal = new Normal();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            normal = JSONUtil.toBean(str,Normal.class);
            if(ObjectUtil.isNotEmpty(normal)){
                flang = true;
            }
        }
        return normal;
    }

    public static Contract contract(String address){
        String str = HttpUtil.get(String.format(TokenViewApi.contract,address.toLowerCase()));
        Contract contract = new Contract();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            contract = JSONUtil.toBean(str,Contract.class);
            if(ObjectUtil.isNotEmpty(contract)){
                flang = true;
            }
        }
        return contract;
    }

    public static TokenEth tokenEth(String address){
        String str = HttpUtil.get(String.format(TokenViewApi.tokenEth,address.toLowerCase()));
        TokenEth tokenEth = new TokenEth();
        Boolean flang = false;
        //Retry mechanism,3 times
        for(int i = 0;i < 4;i++){
            if(flang){
                break;
            }
            tokenEth = JSONUtil.toBean(str,TokenEth.class);
            if(ObjectUtil.isNotEmpty(tokenEth)){
                flang = true;
            }
        }
        return tokenEth;
    }

    public static void main(String[] args) {
//        Long i = System.currentTimeMillis();
//        Token token = gettokens();
//        List<Data> data = token.getData().getData();
//        for(){
//            List<Data> data1 = data.stream().filter(s -> s.getTokenaddress().equalsIgnoreCase("0x39013f961c378f02c2b82a6e1d31e9812786fd9d")).collect(Collectors.toList());
//        }
//
//        Long j = System.currentTimeMillis();
//        System.out.println(j-i);
//        System.out.println(data1.get(0).getPrice());
    }
}
