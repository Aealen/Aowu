package org.mecca.api;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.mecca.util.HttpUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PingDomain {

//    {"success":true,
//     "address":"浙江-绍兴 (电信)",
//     "host":"baidu.com",
//     "info":
//        {"Ip":"220.181.38.148",
//                "Ttl":"51",
//                "Time":"30.00 ms",
//                "ipAddress":"中国北京 电信"
//        }
//    }
    public static String Get(String flag){
        String result = null;
        String url ="https://api.vvhan.com/api/ping?url=baidu.com";
        String RsltMsg="Ping --- Result\n";

        try {
            result = HttpUtil.sendGet(url, "utf-8");
            JSONObject json = JSONObject.fromObject(result);
            if (json.getBoolean("success")==true) {
                String address= json.getString("address");
                String host= json.getString("host");
                JSONObject jb=json.getJSONObject("info");
                String ip=jb.getString("Ip");
                String Ttl=jb.getString("Ttl");
                String Time=jb.getString("Time");
                String ipAddress=jb.getString("ipAddress");


                return RsltMsg+ "目标地址: "+address+"\n目标IP: "+host+"Info:\n主机IP:"+ip+"\nTtl: "+Ttl+"\nTime: "+Time+"\n主机地址: "+ipAddress;
            }else {
                System.out.println(String.valueOf(json));
                return json.getString("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "PingAPI has something wrong ,please Contact the author`s QQ: 1592769715";
    }

    public static void main(String[] args) {
        System.out.println(Get("baidu.com"));
    }

}
