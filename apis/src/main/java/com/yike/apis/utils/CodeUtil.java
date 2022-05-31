package com.yike.apis.utils;

import java.util.Random;

public class CodeUtil {
    public static String getCode(){
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10); //Pick a random number at a time（0-9）
            code = code + r;  //Put the numbers together at random each time
        }
        return code;
    }
}
