package org.mecca.Func;

import org.mecca.api.ICPCheck;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class PingFunc {
    public static void main(String[] args) {
        System.out.println("\n----------------\n"+run("bilibili.com"));
    }
    public static boolean ping(String ipAddress) throws Exception {
        int  timeOut =  4000 ;  //超时4s以上
        //isReachable(timeout)的方法，用于测试是否可以到达该地址（主机）
        boolean status = InetAddress.getByName(ipAddress).isReachable(timeOut);
        // 当返回值是true时，说明host是可用的，false则不可。
        return status;
    }

    public static Long pingTime(String ipAddress) throws IOException {
        int  timeOut =  4000 ;  //超时4s以上
        long BeforeTime = System.currentTimeMillis();
        InetAddress.getByName(ipAddress).isReachable(timeOut);
        long AfterTime = System.currentTimeMillis();
        Long TimeDifference = AfterTime - BeforeTime;
        System.out.println(TimeDifference);
        return TimeDifference;
    }

    public static String run(String flag){

        boolean ping;
        InetAddress indress;
        Long time;

        if (!flag.matches("^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$")){
            return "ip输入格式错误    正确示例: mecca.org.cn";
        }
        try {
            //通过InetAddress类访问主机名,ip地址
            indress = InetAddress.getByName(flag);//获取对应Ip的inetAddress对象 存储了IP的主机名和ip地址
            System.out.println("----------------");
            time=pingTime(flag);
            ping = ping(flag);
            System.out.println(ping);


        } catch (Exception e) {
            return "这个ip地址不可用   正确示例: mecca.org.cn";
        }
        return "Ping  --  "+flag+"\n" +
                "Host/ip:  "+indress+"\n"+
                "succes:   "+ping+"\n" +
                "Time:     "+time+"ms";
    }

}
