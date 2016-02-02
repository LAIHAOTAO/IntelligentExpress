package com.ericlai.express.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/2/2.
 */
public class GetBeanMap {

    private static Logger log = LogManager.getLogger();

    /**
     * 遍历一个bean类，以map的形式返回它的全部field和field的值
     * @param obj
     * @return
     */
    public static Map<String, String> getBeanFieldAndValue(Object obj) {
        Map<String, String> map = new HashMap<>();
        Class c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object tmp = field.get(obj);
                String key = getShortName(field.toString());
                log.debug(key);
                log.debug(tmp.toString());
                map.put(key, tmp.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    private static String getShortName(String str) {
        int begin = str.lastIndexOf(".") + 1;
        return str.substring(begin, str.length());
    }
}
