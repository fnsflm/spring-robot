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
//        long userId = event.getUserId();
        long groupId = event.getGroupId();

        String keyword = "";
        int count = 1;
        if (msg.contains("栞栞"))
            return MESSAGE_IGNORE;
        if (msg.matches("[色涩]图\\d*( .*)?")) {
            String[] msgs = msg.split(" ");
            if (msgs.length > 1) {
                keyword = msgs[1];
            }
            if (msgs[0].length() > 2) {
                count = Integer.parseInt(msgs[0].substring(2));
            }
        } else if (msg.matches(".*[涩色]图.*") || msg.matches(".*不够[色涩].*")) {
            keyword = "";
        } else {
            return MESSAGE_IGNORE;
        }
        // 个别群禁止色图功能
        if (groupId == 490943205L) {
            cq.sendGroupMsg(groupId, "色图禁止nanodesu!(请移步开车群766625087)", false);
            return MESSAGE_IGNORE;
        }
        // 获取json, 存到类变量setuRequest中
        try {
            getloliapp(keyword);
        } catch (Exception e) {
            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
            e.printStackTrace();
            cq.sendGroupMsg(groupId, "请求失败", false);
        }
        // 发送setu
        try {
            if (setuRequest.getCount() == 0) {
                cq.sendGroupMsg(groupId, setuRequest.getMsg(), false);
            } else
                sendSetu(groupId, cq, count);
        } catch (Exception e) {
            e.printStackTrace();
            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
            cq.sendGroupMsg(groupId, "图片发送失败", false);
        }
        // 插入setu到数据库
//        try {
//            insertSetu();
//        } catch (Exception e) {
//            e.printStackTrace();
//            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, e.toString(), false);
//        }
        return MESSAGE_BLOCK;
    }

    void sendSetu(Long groupId, CoolQ cq, int count) {
        // 测试打印setu
//        for(Setu setu:setuRequest.getData()){
//            cq.sendGroupMsg(groupId,"标题：" + setu.getTitle() + "\n作者：" + setu.getAuthor() + "\n链接：" + setu.getUrl() + "\n剩余次数：" + setuRequest.getQuota()+"\ntags: "+setu.getTags(),false);
//            cq.sendGroupMsg(groupId,CQCode.image(setu.getUrl()),false);
//        }
        List<Setu> setus = setuRequest.getData();
        int cnt = Math.min(count, setuRequest.getCount());
        for (int i = 0; i < cnt; i++) {
            cq.sendGroupMsg(groupId, "标题：" + setus.get(i).getTitle() + "\npid: " + setus.get(i).getPid() + "\n作者：" + setus.get(i).getAuthor() + "\n链接：" + setus.get(i).getUrl() + "\ncount: " + (i + 1) + "/" + cnt + "\n剩余次数：" + setuRequest.getQuota() + "\ntags: " + setus.get(i).getTags(), false);
            cq.sendGroupMsg(groupId, CQCode.image(setus.get(i).getUrl()), false);
//            cq.sendGroupMsg(groupId, "[CQ:image,url=https://i.pixiv.cat/img-master/img/2018/01/07/02/11/54/66681542_p0_master1200.jpg]", false);
//            System.out.println(CQCode.image(setus.get(i).getUrl()));
//            CQGlobal.robots.get(1132492036L).sendGroupMsg(654839559L, setus.get(i).getJson(), false);
        }
    }

    void insertSetu() {
        for (Setu setu : setuRequest.getData()) {
            SQLPlugin.insert("setu", setu.getPid() + "", setu.getUrl(), setu.getJson());
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
        con.setRequestProperty("Charsert", "UTF-8");
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
}
