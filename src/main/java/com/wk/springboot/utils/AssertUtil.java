package com.wk.springboot.utils;

import com.mysql.cj.exceptions.SSLParamsException;

public class AssertUtil {
    public static void isTrue(Boolean flag, String msg){
        if(flag)
            throw new SSLParamsException(msg);
    }
}
