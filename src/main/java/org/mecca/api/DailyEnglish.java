package org.mecca.api;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.mecca.util.NormalPost;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DailyEnglish {


    public static String Get() {
        //这里需要替换为你自己的appid和secret，你可以在这里找到 https://www.showapi.com/console#/myApp
        String showapi_appid = "763845";
        String showapi_sign = "34b722933b184d198737808165c21fb5";
        //这里的参数在对应接口的页面中查看(接口文档==>二、请求参数==>应用级参数)
        String count = "1";
        //拼接接口所需的参数
        String paramsStr = "showapi_appid=" + showapi_appid + "&showapi_sign=" + showapi_sign + "&count=" + count;
        //调用接口
        String result = NormalPost.sendPost("https://route.showapi.com/1211-1", paramsStr, "utf-8");
        //得到返回参数

        JSONObject json = JSONObject.fromObject(result);
        if (json.getInt("showapi_res_code") != 0) {
            System.out.println(json.getString("showapi_res_error"));
        } else {
            JSONArray jsonArray = json.getJSONObject("showapi_res_body").getJSONArray("data");
            JSONObject jb = (JSONObject) jsonArray.getJSONObject(0);
            String english = jb.getString("english");
            String chinese = jb.getString("chinese");

            return english+"\n"+chinese;
        }
        return "每日一句英语短句出现问题,请联系作者QQ:1592769715";
    }


    public static void main(String[] args) {
        System.out.println(Get());
    }
}
