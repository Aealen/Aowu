package org.mecca.Func;

public class TestInputFunc {

    public static void main(String[] args) {
        String command ="/EncodeWithSalt 方明轩 112";
        String[] split = new String[0];
        if (command.matches("/EncodeWithSalt .*"))
         split= command.split("/EncodeWithSalt ");

        for (String s : split) {
            System.out.println(s);
        }
        String[] cmdrs=split[1].split(" ");

        System.out.println(cmdrs[0]);
        System.out.println(cmdrs[1]);




    }



}
