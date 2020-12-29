package com.example.demo.plugin;

import com.example.demo.SQLplugin.SQLPlugin;
import com.example.demo.setu.SetuPlugin;
import net.lz1998.cq.CQGlobal;
import net.lz1998.cq.robot.CQPlugin;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulePlugin extends CQPlugin{
//    public static HashMap<String,String> map = getmap();
//    public static boolean mapread = false; //task4

//    HashMap<String,String> map = getmap();

    @Scheduled(cron = "0 50 11,17,23 * * ? ",zone = "Asia/Shanghai")
    public void task1(){
        CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L,"演习次数要增加了nanodesu,大家快去乱杀吧",false);
    }
    @Scheduled(cron = "0 0 21 * * ? ",zone = "Asia/Shanghai")
    public void task2(){
        CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L,"夜间委托开始了nanodesu",false);
    }
    @Scheduled(cron = "0 0 23 * * ? ",zone = "Asia/Shanghai")
    public void task3(){
        CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L,"每日任务、困难别忘了nanodesu",false);
    }
    @Scheduled(cron = "0 0 0 * * ? ",zone = "Asia/Shanghai")
    public void clearRP(){SQLPlugin.delete("lucky");}
//    @Scheduled(cron = "0 0/1 * * * ? ", zone = "Asia/Shanghai")
//    public void task5(){
//        //System.out.println("running");
//        if(mapread==false){
//            try{
//                File f = new File("src/main/resources/uplist.txt");
//                BufferedReader br = new BufferedReader(new FileReader(f));
//                while(true){
//                    String s = br.readLine();
//                    if(s==null)break;
//                    String ss[] = s.split(" ");
//                    map.put(ss[1],getDid(ss[1]));
//                }
//            }catch(Exception e){
//                CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L,"error:定时任务",false);
//            }
//            mapread=true;
//        }
//            Iterator it = map.entrySet().iterator();
//            while (it.hasNext()){
//                Map.Entry entry = (Map.Entry) it.next();
//                String key = (String) entry.getKey();
//                String value = (String) entry.getValue();
//                //System.out.println(getDid(key));
//                if(!value.equals(getDid(key))){
//                    map.replace(key,getDid(key));
//                    //String uid = Bili.map.get(key);
//                    String cmd = "python3 src/main/resources/getbilibili.py "+ key + " "+0;
//                    try{
//                        Process p = Runtime.getRuntime().exec(cmd);
//                        InputStream fis=p.getInputStream();
//                        InputStreamReader isr=new InputStreamReader(fis);
//                        BufferedReader br=new BufferedReader(isr);
//                        String did = br.readLine();
//                        String content = br.readLine();
//                        if(content==null){
//                            content = "null";
//                        }else
//                            content = content.substring(1,content.length()-1);
//                        String uname = br.readLine();
//                        String sendst = uname+"的最新动态：\n内容：\n"+content.replaceAll("\\\\n","\n");
//                        String url = "链接地址："+"https://t.bilibili.com/"+did;
//                        for(int tmp=0; tmp < sendst.length()/150 ;tmp++){
//                            CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L,sendst.substring(tmp*150, (tmp+1)*150), false);
//                        }
//                        if(sendst.length()%150!=0)
//                            CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L, sendst.substring((sendst.length()/150)*150),false);
//                        //CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L,sendst,false);
//                        CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L,url,false);
//                    }
//                    catch (IOException e) {
//                        CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L,"error：定时任务",false);
//                    }
//                }
//            }
//    }
//    public String getDid(String uid){
//        String cmd = "python3 src/main/resources/getbilibili.py "+ uid + " "+0;
//        try{
//            Process p = Runtime.getRuntime().exec(cmd);
//            InputStream fis=p.getInputStream();
//            InputStreamReader isr=new InputStreamReader(fis);
//            BufferedReader br=new BufferedReader(isr);
//            return br.readLine();
//        }
//        catch (IOException e) {
//            return "error：getDid";
//        }
//    }
//    @Scheduled(cron = "0 0/1 * * * ? ", zone = "Asia/Shanghai")
//    public void task4(){
//        System.out.println("schedule is ok");
//        String s[] = {"110969 玄虚小圣","4305299 井号5467","233114659 碧蓝航线"};
//        for (String ss:s) {
//            try {
//                Biligetdym xs = new Biligetdym(ss.split(" ")[0]);
//                if(!xs.getRid().equals(map.get(ss.split(" ")[0]))){
//                    map.replace(ss,xs.getRid());
//                    CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L,ss.split(" ")[1]+"的最新动态：\n\n"+ xs.getContent()+"\n\n链接地址："+"https://t.bilibili.com/"+xs.getRid(),false);
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//    }
//    public static HashMap<String,String> getmap(){
//        HashMap<String,String> tmp = new HashMap<>();
//        putmap(tmp,"110969");// 小圣
//        putmap(tmp,"4305299"); //井号
//        putmap(tmp,"233114659"); //b博
//        return tmp;
//    }
//    public static void putmap(HashMap<String,String> tmp,String uid){
//        try {
//            Biligetdym xs = new Biligetdym(uid);
//            tmp.put(uid,xs.getRid());
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//    @Scheduled(cron = "0 0,30 * * * ? ",zone = "Asia/Shanghai")
//    public void cycleSetu(){
//        try{
////            String[] setu = SetuPlugin.getloliapp("");
////            CQGlobal.robots.get(1132492036L).sendGroupMsg(1067471239L, "定时setu\n标题：" + setu[0] + "\n作者：" + setu[1] + "\n链接：" + setu[2] + "\n剩余次数：" + setu[3], false);
////            (new SetuPlugin.MyThread(1067471239L,CQGlobal.robots.get(1132492036L),setu)).start();
//        }catch(Exception e){
//            e.printStackTrace();
//            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
//        }
//
//    }
}
