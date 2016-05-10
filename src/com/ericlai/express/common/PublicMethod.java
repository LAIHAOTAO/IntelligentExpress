package com.ericlai.express.common;

import com.ericlai.express.util.GetBeanMap;
import com.ericlai.express.util.JsonBuildUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/2/2.
 */
public class PublicMethod {

    /**
     * 将json格式的数据返回前端
     * @param response
     * @param json json格式数据
     */
    public static void SendJsonToFront(HttpServletResponse response, String json) {
        PrintWriter out = null;
        //设置格式
        response.setContentType("application/json");
        try {
            //获取输出的格式
            out = response.getWriter();
            //输出json格式的字符串到前端
            out.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Ajax请求回复的数据
     * @param list
     * @param type
     * @return
     */
    public static String getAjaxResponse(List<?> list, String type) {
        ArrayList<String> subKey = new ArrayList<>();
        ArrayList<Map<String,String>> subValue = new ArrayList<>();
        Map<String, String> mainMap = new HashMap<>();
        mainMap.put("result", "success");
        subKey.add(type);
        //遍历数据库查出来的所有记录
        for (Object aList : list) {
            Map<String, String> utilMap = GetBeanMap.getBeanFieldAndValue(aList);
            subValue.add(utilMap);
        }
        return JsonBuildUtil.packToObject(mainMap, subKey, subValue);
    }

    public static void sendStringToFront(HttpServletResponse response, String string) {
        PrintWriter out = null;
        //设置格式
        response.setContentType("application/text");
        try {
            //获取输出的格式
            out = response.getWriter();
            //输出json格式的字符串到前端
            out.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
