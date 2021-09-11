package org.mecca;


import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.mecca.api.DailyEnglish;
import org.mecca.api.ICPCheck;
import org.mecca.api.TodayInHistory;
import org.mecca.util.DateUtil;

public final class Aowu extends JavaPlugin {
    public static final Aowu INSTANCE = new Aowu();
    private Aowu() {
        super(new JvmPluginDescriptionBuilder("org.mecca.Aowu", "1.0").build());
    }

    //用此方式实现每天只发一次
    public int countEnglish= DateUtil.getDateByDay();  //若数值等于当前日期则发送English  初始日期为插件运行日期
    public int countHistory= DateUtil.getDateByDay();  //若数值等于当前日期则发送History  初始日期为插件运行日期
    public ICPCheck icpCheck= new ICPCheck();





    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");



        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class,(FriendMessageEvent event)->{
            String command = event.getMessage().contentToString();
            /**
             * 备案查询
             */
            if (command.contains("/ICP")){
                event.getSubject().sendMessage(ICPCheck.Get(command.substring(5)).toString());
            }
            /**
             * 手动触发 每日英语和那年今日
             */
            if (event.getMessage().contentToString().contains("/DailyEnglish")){
                event.getSubject().sendMessage(DailyEnglish.Get()+"\n测试命令!!! 每日十点前发送每日英语!!");
            }
            if (event.getMessage().contentToString().contains("/TodayInHis")){
                event.getSubject().sendMessage(TodayInHistory.Get()+"\n测试命令!!! 每日十点前发送那年今日!!");
            }
            /**
             * 每日英语和那年今日
             */
            //判断计数器与当前日期是否一致
            if (countHistory== DateUtil.getDateByDay()) {
                //那年今日
                if (DateUtil.getTimeByHour()>=10){
                    getLogger().info("TodayInHistory Sended!!!");
                    event.getSubject().sendMessage(TodayInHistory.Get());
                }
                if (DateUtil.isEndOfMonth(countHistory)) {
                    getLogger().info("countHistory set to 1!!");
                    countHistory=1;
                }else {
                    getLogger().info("countHistory ++");
                    countHistory++; //日期加1
                }
            }

            if (countEnglish== DateUtil.getDateByDay()) {
                //每日英语
                if (DateUtil.getTimeByHour()<10){
                    getLogger().info("DailyEnglish Sended!!!");
                    event.getSubject().sendMessage(DailyEnglish.Get());
                }
                if (DateUtil.isEndOfMonth(countEnglish)){
                    getLogger().info("countEnglish set to 1!!");
                    countEnglish=1;
                }else {
                    getLogger().info("countEnglish ++");
                    countEnglish++;
                }

            }






        });
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class,(GroupMessageEvent event)->{
            /**
             * 备案查询
             */
            String command = event.getMessage().contentToString();
            if (command.contains("/ICP")){
                event.getSubject().sendMessage(ICPCheck.Get(command.substring(5)).toString());
            }
            /**
             * 手动触发 每日英语和那年今日
             */
            if (event.getMessage().contentToString().contains("/DailyEnglish")){
                event.getSubject().sendMessage(DailyEnglish.Get()+"\n测试命令!!! 每日十点前发送每日英语!!");
            }
            if (event.getMessage().contentToString().contains("/TodayInHis")){
                event.getSubject().sendMessage(TodayInHistory.Get()+"\n测试命令!!! 每日十点前发送那年今日!!");
            }
            /**
             * 每日英语和那年今日
             */
            //判断计数器与当前日期是否一致
            if (countHistory== DateUtil.getDateByDay()) {
                //那年今日
                if (DateUtil.getTimeByHour()>=10){
                    getLogger().info("TodayInHistory Sended!!!");
                    event.getSubject().sendMessage(TodayInHistory.Get());
                }
                if (DateUtil.isEndOfMonth(countHistory)) {
                    getLogger().info("countHistory set to 1!!! ");
                    countHistory=1;
                }else {
                    getLogger().info("countHistory ++");
                    countHistory++; //日期加1
                }
            }

            if (countEnglish== DateUtil.getDateByDay()) {
                //每日英语
                if (DateUtil.getTimeByHour()<10){
                    getLogger().info("DailyEnglish Sended!!!");
                    event.getSubject().sendMessage(DailyEnglish.Get());
                }
                if (DateUtil.isEndOfMonth(countEnglish)){
                    getLogger().info("countEnglish set to 1!!");
                    countEnglish=1;
                }else {
                    getLogger().info("countEnglish ++");
                    countEnglish++;
                }

            }









        });




//        /**
//         * 每日英语和那年今日
//         */
//        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class,(GroupMessageEvent event) -> {
//            //判断计数器与当前日期是否一致
//            if (countHistory== DateUtil.getDateByDay()) {
//                //那年今日
//                if (DateUtil.getTimeByHour()>=10){
//                    getLogger().info("TodayInHistory Sended!!!");
//                    event.getSubject().sendMessage(TodayInHistory.Get());
//                }
//                if (DateUtil.isEndOfMonth(countHistory)) {
//                    getLogger().info("countHistory set to 1!!");
//                    countHistory=1;
//                }else {
//                    getLogger().info("countHistory ++");
//                    countHistory++; //日期加1
//                }
//            }
//
//            if (countEnglish== DateUtil.getDateByDay()) {
//                //每日英语
//                if (DateUtil.getTimeByHour()<10){
//                    getLogger().info("DailyEnglish Sended!!!");
//                    event.getSubject().sendMessage(DailyEnglish.Get());
//                }
//                if (DateUtil.isEndOfMonth(countEnglish)){
//                    getLogger().info("countEnglish set to 1!!");
//                    countEnglish=1;
//                }else {
//                    getLogger().info("countEnglish ++");
//                    countEnglish++;
//                }
//
//            }
//        });
//        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class,(FriendMessageEvent event) -> {
//            //判断计数器与当前日期是否一致
//            if (countHistory== DateUtil.getDateByDay()) {
//                //那年今日
//                if (DateUtil.getTimeByHour()>=10){
//                    getLogger().info("TodayInHistory Sended!!!");
//                    event.getSubject().sendMessage(TodayInHistory.Get());
//                }
//                if (DateUtil.isEndOfMonth(countHistory)) {
//                    getLogger().info("countHistory set to 1!!");
//                    countHistory=1;
//                }else {
//                    getLogger().info("countHistory ++");
//                    countHistory++; //日期加1
//                }
//            }
//
//            if (countEnglish== DateUtil.getDateByDay()) {
//                //每日英语
//                if (DateUtil.getTimeByHour()<10){
//                    getLogger().info("DailyEnglish Sended!!!");
//                    event.getSubject().sendMessage(DailyEnglish.Get());
//                }
//                if (DateUtil.isEndOfMonth(countEnglish)){
//                    getLogger().info("countEnglish set to 1!!");
//                    countEnglish=1;
//                }else {
//                    getLogger().info("countEnglish ++");
//                    countEnglish++;
//                }
//
//            }
//        });


    }


}