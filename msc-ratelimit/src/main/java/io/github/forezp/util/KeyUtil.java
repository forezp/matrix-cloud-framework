package io.github.forezp.util;


import io.github.forezp.entity.LimitEntity;

/**
 * Email miles02@163.com
 *
 * @author fangzhipeng
 * create 2018-06-26
 **/
public class KeyUtil {

    public static String compositeKey(String identifier, String key) {
        return identifier + ":" + key + ":";
    }


    public static String getKey(LimitEntity limitEntity){
        String key ;
        switch (limitEntity.getLimitType()) {
            case GLOBAL:
                key = "global";
                break;
            case SERVICE:
                key = limitEntity.getName();
                break;
            default:
                key = null;
                break;
        }
        return key;
    }



}
