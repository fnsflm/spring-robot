package com.example.demo.SQLplugin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SQLimgPlugin {
    public static boolean download(String imgUrl, String path) {
        InputStream is = null;
        try{
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(imgUrl).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            httpURLConnection.setRequestProperty("Referer","no-referrer");
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(20000);
            is = httpURLConnection.getInputStream();
        }catch(IOException e){
            System.out.println("ioerror-web-request");
            return false;
        }
        try {
            BufferedImage img = ImageIO.read(is);
            ImageIO.write(img,"jpg",new File("./loli.jpg"));
            is.close();
        }catch (IOException e){
            System.out.println("ioerror-img-download");
            return false;
        }
        return true;
    }
}
