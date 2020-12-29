package com.example.demo.setu;

import com.example.demo.SQLplugin.SQLPlugin;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.lz1998.cq.CQGlobal;
import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

@Component
public class SetuPlugin extends CQPlugin {
    SetuRequest setuRequest;

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long userId = event.getUserId();
        long groupId = event.getGroupId();
        String keyword="";
        int count = 1;
        if (msg.matches("[色涩]图\\d*( .*)?")) {
            String[] msgs =  msg.split(" ");
            if(msgs.length>1){
                keyword=msgs[1];
            }
            if(msgs[0].length()>2){
                count = Integer.parseInt(msgs[0].substring(2));
            }
        } else if (msg.matches(".*[涩色]图.*") || msg.matches(".*不够[色涩].*")) {
            keyword = "";
        } else {
            return MESSAGE_IGNORE;
        }
        try {
            getloliapp(keyword);
        } catch (Exception e) {
            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
            e.printStackTrace();
            cq.sendGroupMsg(groupId, "请求失败", false);
        }
        try {
            sendSetu(groupId,cq,count);
        } catch (Exception e) {
            e.printStackTrace();
            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
            if (setuRequest.getCode() == 404) {
                cq.sendGroupMsg(groupId, setuRequest.getMsg(), false);
            } else {
                cq.sendGroupMsg(groupId, "图片发送失败", false);
            }
        }
        try{
            insertSetu();
        }catch(Exception e){
            e.printStackTrace();
            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
        }
        return MESSAGE_BLOCK;

//        if(msg.matches("\\[CQ:at,qq=1132492036\\]" + ".*[涩色]图")||msg.matches("^@nanodesu.*[涩色]图")||msg.matches("^羽入[来发]张[涩色]图")){
//        if (msg.matches(".*[涩色]图.*") || msg.matches(".*不够[色涩].*")) {
//            String ss[] = null;
//            try {
////                ss = getloliapp("");
////                cq.sendGroupMsg(groupId, "标题：" + ss[0] + "\n作者：" + ss[1] + "\n链接：" + ss[2] + "\n剩余次数：" + ss[3], false);
//            } catch (Exception e) {
//                e.printStackTrace();
//                CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
//                cq.sendGroupMsg(groupId, "请求失败", false);
//            }
//            if (ss != null) {
//                MyThread mt = new MyThread(groupId, cq, ss);
//                mt.start();
//            }
//            return MESSAGE_BLOCK;
//        }
    }

    void sendSetu(Long groupId,CoolQ cq,int count){
//        for(Setu setu:setuRequest.getData()){
//            cq.sendGroupMsg(groupId,"标题：" + setu.getTitle() + "\n作者：" + setu.getAuthor() + "\n链接：" + setu.getUrl() + "\n剩余次数：" + setuRequest.getQuota()+"\ntags: "+setu.getTags(),false);
//            cq.sendGroupMsg(groupId,CQCode.image(setu.getUrl()),false);
//        }
        List<Setu> setus = setuRequest.getData();
        for(int i = 0; i < count && i < setuRequest.getCount(); i++){
            cq.sendGroupMsg(groupId,"标题：" + setus.get(i).getTitle() + "\npid: "+setus.get(i).getPid()+ "\n作者：" + setus.get(i).getAuthor() + "\n链接：" + setus.get(i).getUrl() + "\n剩余次数：" + setuRequest.getQuota()+"\ntags: "+setus.get(i).getTags(),false);
            cq.sendGroupMsg(groupId,CQCode.image(setus.get(i).getUrl()),false);
//            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, setus.get(i).getJson(), false);
        }
    }
    void insertSetu(){
        for(Setu setu:setuRequest.getData()){
            SQLPlugin.insert("setu",setu.getPid()+"", setu.getUrl(),setu.getJson());
        }
    }

    void getloliapp(String keyword) throws IOException {
        String url = "https://api.lolicon.app/setu/";
        url = url + "?apikey=949160985f9f9a2a687654&r18=2&size1200=1&num=10";
        if (!keyword.equals("")) {
            keyword = URLEncoder.encode(keyword, "utf-8");
            url = url + "&keyword=" + keyword;
        }
        URLConnection con = (new URL(url)).openConnection();
        con.connect();
        InputStream in = con.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer sb = new StringBuffer();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        isr.close();
        str = sb.toString();
        setuRequest = (new Gson()).fromJson(str, SetuRequest.class);
    }

//    public static String[] getloliapp(String keyword) throws IOException {
//        String url = "https://api.lolicon.app/setu/";
//        url = url + "?apikey=949160985f9f9a2a687654&r18=2&size1200=1";
//        if (!keyword.equals("")) {
//            keyword = URLEncoder.encode(keyword, "utf-8");
//            url = url + "&keyword=" + keyword;
//        }
//        URLConnection con = (new URL(url)).openConnection();
//        con.connect();
//        InputStream in = con.getInputStream();
//        InputStreamReader isr=new InputStreamReader(in);
//        BufferedReader br=new BufferedReader(isr);
//        StringBuffer sb=new StringBuffer();
//        String str=null;
//        while((str=br.readLine())!=null){
//            sb.append(str);
//        }
//        br.close();
//        isr.close();
//        str = sb.toString();
//        JsonParser parser = new JsonParser();
//        String ss[] = new String[6];
//        ss[4] = str;
//        CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, str, false);
//        JsonObject js = (JsonObject) parser.parse(str);
//        ss[3] = js.get("quota").getAsString();
//        js = js.get("data").getAsJsonArray().get(0).getAsJsonObject();
//        ss[0] = js.get("title").getAsString();
//        ss[1] = js.get("author").getAsString();
//        ss[2] = js.get("url").getAsString();
//        ss[5] = js.get("pid").getAsString();
//        System.out.println(js.get("url").getAsString());
//        in.close();
//        return ss;
//    }

    //    private static String appendUrl(String url, String key, String value) {
//        String newUrl = url;
//        StringBuffer param = new StringBuffer();
//        param.append(key + "=" + value + "&");
//        String paramStr = param.toString();
//        paramStr = paramStr.substring(0, paramStr.length() - 1);
//        if (newUrl.indexOf("?") >= 0) {
//            newUrl += "&" + paramStr;
//        } else {
//            newUrl += "?" + paramStr;
//        }
//        return newUrl;
//    }
//    public static class MyThread extends Thread {
//        long groupId;
//        CoolQ cq;
//        String[] s;
//
//        @Override
//        public void run() {
////            System.out.println(s[5]+s[2]+s[4]);
////            System.out.println(CQCode.image(s[2]));
////            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L,s[5]+s[2]+s[4],false);
////            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L,CQCode.image(s[2]),false);
//            cq.sendGroupMsg(groupId, CQCode.image(s[2]), false);
//            SQLPlugin.insert("setu", s[5], s[2], s[4]);
//        }
//
//        MyThread(long id, CoolQ c, String[] ss) {
//            groupId = id;
//            cq = c;
//            s = ss;
//        }
//    }
}
