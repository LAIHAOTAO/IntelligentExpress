package com.ericlai.express.util;

import java.util.Date;

/**
 * Created by Administrator on 2016/2/2.
 */
public class IdBuilder {

    public static String getPersonId() {
        Date date = new Date();
        long dateTime = date.getTime();
        return String.valueOf(dateTime);
    }

    public static String getPackageId() {
        String str = IdBuilder.getPersonId();
        return "p" + str;
    }
}
