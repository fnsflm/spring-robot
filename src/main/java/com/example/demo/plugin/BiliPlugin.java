package com.example.demo.plugin;

import net.lz1998.cq.CQGlobal;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Random;

@Component
public class BiliPlugin extends CQPlugin {
//    static HashMap<String,String> map = new HashMap<>();
//    static boolean readlist = false;
//    @Override
//    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
//        String msg = event.getMessage();
//        Long userId = event.getUserId();
//        Long groupId = event.getGroupId();
//        if(readlist==false){
//            readmapnow();
//            readlist=true;
//        }
//        if(msg.contains("addbzdt")){
//            try{
//                File f = new File("uplist.txt");
//                BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
//                String sss[] = msg.substring(msg.indexOf(" ")+1).split(" ");
//                bw.write(sss[0]+" "+sss[1]+"\n");
//                bw.flush();
//                bw.close();
//                cq.sendGroupMsg(groupId,"已添加",false);
//                map.put(sss[0],sss[1]);
//                SchedulePlugin.mapread=false;
//            }catch (Exception e){
//                cq.sendGroupMsg(groupId,"error：bili",false);
//            }
//            return MESSAGE_BLOCK;
//        }
//        if(!msg.contains("bzdt"))
//            return MESSAGE_IGNORE;
//        else{
//            String ss[] = msg.split(" ");
//            String cmd;
//            if(!map.containsKey(ss[1])){
//                cq.sendGroupMsg(groupId,"未添加uid，addbzdt+空格+up主名称+空格+uid可添加",false);
//                return MESSAGE_BLOCK;
//            }
//            if(ss.length==2){
//                cmd = "python3 getbilibili.py "+ map.get(ss[1])  + " "+0;
//            }else{
//                cmd = "python3 getbilibili.py "+ map.get(ss[1])  +" "+ ss[2];
//            }
//            try{
//                Process p = Runtime.getRuntime().exec(cmd);
//                InputStream fis=p.getInputStream();
//                InputStreamReader isr=new InputStreamReader(fis);
//                BufferedReader br=new BufferedReader(isr);
////                String line=null;
////                while((line=br.readLine())!=null){
////                    System.out.println(line);
////                }
//                String did = br.readLine();
//                String content = br.readLine();
//                if(content==null){
//                    content = "null";
//                }else
//                    content = content.substring(1,content.length()-1);
//                //System.out.println(content.replaceAll("\\\\n","\n"));
//                String sendst = "内容：\n"+content.replaceAll("\\\\n","\n");
//                String url = "链接地址："+"https://t.bilibili.com/"+did;
//                for(int tmp=0; tmp < sendst.length()/150 ;tmp++){
//                    cq.sendGroupMsg(groupId,sendst.substring(tmp*150, (tmp+1)*150), false);
//                }
//                if(sendst.length()%150!=0)
//                    cq.sendGroupMsg(groupId, sendst.substring((sendst.length()/150)*150),false);
//                cq.sendGroupMsg(groupId, url, false);
//            }
//            catch (IOException e) {
//                cq.sendGroupMsg(groupId,"error：bili",false);
//            }
//            return MESSAGE_BLOCK;
//        }
//    }
//    public static void readmapnow(){
//        File f = new File("uplist.txt");
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(f));
//            while(true){
//                String s = br.readLine();
//                if(s==null)break;
//                String ss[] = s.split(" ");
//                map.put(ss[0], ss[1]);
//            }
//        }catch(Exception e){
//            System.out.println("error!");
//            CQGlobal.robots.get(1132492036L).sendPrivateMsg(1094159280L,"error",false);
//        }
//    }

}
