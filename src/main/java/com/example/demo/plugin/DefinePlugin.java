package com.example.demo.plugin;

import com.example.demo.SQLplugin.SQLPlugin;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;

@Component
public class DefinePlugin extends CQPlugin {
    public static HashMap<String, String> map = new HashMap<>();
    public static boolean readok = false;

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();
//        if(readok == false){
//            readmap(cq,groupId);
//            readok = true;
//        }
//        if(msg.matches("^定义 .+")){
//            String strs[] = msg.split(" ");
//            //System.out.println("1:"+msg);
//            if(strs.length == 2){
//                map.remove(strs[1]);
//                cq.sendGroupMsg(groupId,"定义清除成功！",false);
//                return MESSAGE_BLOCK;
//            }else{
//                int idx1 = msg.indexOf(' ');
//                int idx2 = msg.substring(idx1+1).indexOf(' ') + idx1 + 1;
//                map.put(msg.substring(idx1+1,idx2),msg.substring(idx2+1));
//                writemap(cq, groupId);
//                cq.sendGroupMsg(groupId,"定义成功！",false);
//                return MESSAGE_BLOCK;
//            }
//        }
//        if(map.get(msg)!=null){
//            cq.sendGroupMsg(groupId,map.get(msg),false);
//            return  MESSAGE_BLOCK;
//        }

        if (msg.matches("^定义 .+")) {
            String strs[] = msg.split(" ");
            if (strs.length == 2) {
                SQLPlugin.delete("define", "name='" + strs[1] + "' and gropid=" + groupId);
                cq.sendGroupMsg(groupId, "定义清除成功！", false);
                return MESSAGE_BLOCK;
            } else {
                int idx1 = msg.indexOf(' ');
                int idx2 = msg.substring(idx1 + 1).indexOf(' ') + idx1 + 1;
                String value = SQLPlugin.find("define", "name='" + msg.substring(idx1 + 1, idx2) + "'", groupId);
                //System.out.println(value);
                if (value != null) {
                    SQLPlugin.delete("define", "name='" + strs[1] + "' and groupid='" + groupId + "'");
                }
                SQLPlugin.insert("define", msg.substring(idx1 + 1, idx2), msg.substring(idx2 + 1), "" + groupId);
                cq.sendGroupMsg(groupId, "定义成功！", false);
                return MESSAGE_BLOCK;
            }
        }
        String value = SQLPlugin.find("define", "name='" + msg + "'", groupId);
        if (value != null) {
            cq.sendGroupMsg(groupId, value, false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("所有定义")) {
            cq.sendGroupMsg(groupId, SQLPlugin.printtable("define", "name", "groupid=" + groupId), false);
        }
        return MESSAGE_IGNORE;
    }

//    public void writemap(CoolQ cq,long groupId){
//        try{
//            File f = new File("src/main/resources/define.txt");
//            BufferedWriter bw = new BufferedWriter(new FileWriter(f,false));
//            for(String k : map.keySet()){
//                bw.write(k + " " + map.get(k) + "\n");
//            }
//            bw.flush();
//            bw.close();
//        }catch (Exception e){
//            cq.sendGroupMsg(654839559L,"error：define!",false);
//        }
//    }
//    public void readmap(CoolQ cq,long groupId){
//        File f = new File("src/main/resources/define.txt");
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(f));
//            while(true){
//                String s = br.readLine();
//                if(s==null)break;
//                String ss[] = s.split(" ");
//                map.put(ss[0], ss[1]);
//            }
//        }catch(Exception e){
//            cq.sendGroupMsg(654839559L,"error：readmap",false);
//        }
//    }
}
