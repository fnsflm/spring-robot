package com.example.demo.plugin;

import com.example.demo.SQLplugin.SQLPlugin;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Random;

@Component
public class RPPlugin extends CQPlugin {
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        Long userId = event.getUserId();
        String msg = event.getMessage();
        Long groupId = event.getGroupId();
        if (msg.equals("今日人品")) {
            String value = SQLPlugin.find("lucky", "qid=" + userId);
            if (value == null) {
                Integer rand = new Random().nextInt(100);
                SQLPlugin.insert("lucky", userId.toString(), rand.toString());
                cq.sendGroupMsg(groupId, CQCode.at(userId) + " 今日人品是" + rand, false);
            } else {
                cq.sendGroupMsg(groupId, CQCode.at(userId) + " 今日人品是" + value, false);
            }
        }
        return MESSAGE_IGNORE;
    }
}

//@Component
//public class RPPlugin extends CQPlugin {
//    public static HashMap<String,String> map = new HashMap<>();
//    public static boolean readmap = false;
//    @Override
//    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
//        if (readmap == false){
//            readmapnow();
//            readmap = true;
//        }
//        Long userId = event.getUserId();
//        String msg = event.getMessage();
//        Long groupId = event.getGroupId();
//        if(msg.equals("今日人品")){
//            if(map.containsKey(String.valueOf(userId))){
//                cq.sendGroupMsg(groupId,CQCode.at(userId) + " 今日人品是" + map.get(String.valueOf(userId)), false);
//            }else{
//                try{
//                    File f = new File("src/main/resources/lucky.txt");
//                    BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
//                    Random r = new Random();
//                    int rand = r.nextInt(100);
//                    bw.write(userId + " " + rand + "\n");
//                    bw.flush();
//                    bw.close();
//                    cq.sendGroupMsg(groupId,CQCode.at(userId) + " 今日人品是" +rand,false);
//                    map.put(String.valueOf(userId),String.valueOf(rand));
//            }catch (Exception e){
//                cq.sendGroupMsg(groupId,"error：人品",false);
//            }}
//            return MESSAGE_BLOCK;
//        }
//        return MESSAGE_IGNORE;
//    }
//    public static void readmapnow(){
//        File f = new File("src/main/resources/lucky.txt");
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(f));
//            while(true){
//                String s = br.readLine();
//                if(s==null)break;
//                String ss[] = s.split(" ");
//                map.put(ss[0], ss[1]);
//            }
//        }catch(Exception e){
//            System.out.println("error：人品");
//        }
//    }
//}
