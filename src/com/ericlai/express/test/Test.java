package com.ericlai.express.test;

import com.ericlai.express.util.JsonBuildUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/30.
 */
public class Test {

    private Logger log = LogManager.getLogger(Test.class.getName());

    @org.junit.Test
    public void testLog() {
        log.debug("test log");
        ArrayList<String> subKey = new ArrayList<>();
        ArrayList<Map<String,String>> subValue = new ArrayList<>();
        Map<String, String> mainMap = new HashMap<>();
        Map<String, String> utilMap1 = new HashMap<>();
        Map<String, String> utilMap2 = new HashMap<>();
        utilMap1.put("name", "eric");
        utilMap1.put("age", "18");
        utilMap2.put("name", "bill");
        utilMap2.put("age", "16");
        mainMap.put("result", "success");
        subKey.add("queryDto");
        subValue.add(utilMap1);
        subValue.add(utilMap2);
        String json = JsonBuildUtil.packToObject(mainMap, subKey, subValue);
        log.debug(json);
    }
}
