package com.ericlai.express.test;

import com.ericlai.express.dto.Person;
import com.ericlai.express.util.GetBeanMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Created by Administrator on 2016/1/30.
 */
public class Test {

    private Logger log = LogManager.getLogger(Test.class.getName());

    @org.junit.Test
    public void testLog() {
        Person person = new Person();
        person.setName("eric");
        person.setGender("male");
        person.setPhone("123456789");
        person.setIdCrl("1");
        person.setLogNm("123456");
        person.setLogPw("123456");
        person.setPersonId("1234567");
        Map<String, String>map = GetBeanMap.getBeanFieldAndValue(person);
        log.debug(map.get("logNm"));

    }


    public void tes1t() {
        String str = "com.ericlai.dto.Person";
        int begin = str.lastIndexOf(".");
        String subStr = str.substring(begin+1, str.length());
        subStr = subStr.toLowerCase();
        log.debug(subStr);
    }
}
