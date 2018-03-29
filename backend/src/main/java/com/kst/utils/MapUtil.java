package com.kst.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

    public static Map generateSuccessRes(Object data) {
        Map map = new HashMap<String, Object>();
        map.put(Constants.ResStatus, 1);
        map.put(Constants.ResResult, data);
        return map;
    }

    public static Map generateFailureRes(String message) {
        Map map = new HashMap<String, Object>();
        map.put(Constants.ResStatus, 0);
        map.put(Constants.ResResult, message);
        return map;
    }
}
