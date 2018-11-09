package com.weitian.utils;

import java.util.Random;

/**
 * Created by Administrator on 2018-11-08.
 */
public class KeyUtil {
    public static String getUniqueKey(){
        Random random=new Random();
        return System.currentTimeMillis()+String.valueOf( random.nextInt(100000)+900000);
    }
}
