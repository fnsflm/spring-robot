//package com.example.demo.setu;
//
//import net.lz1998.cq.CQGlobal;
//import net.lz1998.cq.robot.CQPlugin;
//import net.lz1998.cq.robot.CoolQ;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class setuSchedule extends CQPlugin {
//
//    @Scheduled(cron = "0 0,30 * * * ? ", zone = "Asia/Shanghai")
//    public void cycleSetu() {
//        System.out.println("setu schedule start!");
//        SetuPlugin setup = new SetuPlugin();
//        try {
//            setup.getloliapp("");
//        } catch (Exception e) {
//            e.printStackTrace();
//            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
//        }
//        // 发送setu
//        try {
//            CQGlobal.robots.get(1132492036L).sendGroupMsg(766625087L, "定时setu", false);
//            setup.sendSetu(766625087L, CQGlobal.robots.get(1132492036L), 3);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
//            CQGlobal.robots.get(1132492036L).sendGroupMsg(766625087L, "图片发送失败", false);
//        }
//        // 插入setu到数据库
//        try {
//            setup.insertSetu();
//        } catch (Exception e) {
//            e.printStackTrace();
//            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
//        }
//        System.out.println("setu schedule end!");
//    }
//}
