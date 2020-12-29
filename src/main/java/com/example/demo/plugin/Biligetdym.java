package com.example.demo.plugin;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Biligetdym {
    String uid;
    String rid;
    String content;

    public Biligetdym(String uid) throws IOException {
        String url = "https://api.vc.bilibili.com/dynamic_svr/v1/dynamic_svr/space_history?host_uid=" + uid + "&offset_dynamic_id=0";
        URLConnection con = (new URL(url)).openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; CIBA;");
        con.connect();
        InputStream in = con.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] b = new byte[102400];
        int i = 0;
        while ((i = in.read(b, 0, b.length)) != -1) {
            byteArrayOutputStream.write(b, 0, i);
        }
        String str = new String(byteArrayOutputStream.toByteArray());
        JsonParser parser = new JsonParser();
        JsonObject js = parser.parse(str).getAsJsonObject();
        JsonObject cards = js.getAsJsonObject("data").getAsJsonArray("cards").get(0).getAsJsonObject();
        str = cards.get("card").getAsString();
        str = str.replaceAll("\\\\\"", "\"");
        JsonObject card = parser.parse(str).getAsJsonObject();
        String content = null;
        try {
            content = card.getAsJsonObject("item").get("content").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("content-error");
            content = "null";
        }
//        System.out.println(content);
        String rpid = cards.get("desc").getAsJsonObject().get("dynamic_id_str").getAsString();
        this.rid = rpid;
        this.uid = uid;
        this.content = content;
//        System.out.println(url);
    }

    public String getUid() {
        return uid;
    }

    public String getRid() {
        return rid;
    }

    public String getContent() {
        return content;
    }
}
