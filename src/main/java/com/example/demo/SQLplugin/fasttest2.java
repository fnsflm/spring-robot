package com.example.demo.SQLplugin;

import com.example.demo.plugin.Biligetdym;

import java.io.IOException;
import java.util.HashMap;

public class fasttest2 {
    public static void main(String[] args) throws IOException {
        Biligetdym xs = new Biligetdym("110969");
        System.out.println("小圣"+"的最新动态：\n\n"+ xs.getContent()+"\n\n链接地址：\nhttps://t.bilibili.com/"+xs.getRid());
        System.out.println(xs.getRid());
    }
}
