package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.*;

@Component
public class BlhxPlugin extends CQPlugin {
    // blhxwiki
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        String msg = event.getMessage();
        long userId = event.getUserId();

        if (msg.matches("^碧蓝wiki.*") || msg.matches("^blhxwiki.*") || msg.matches("^blwk.*")) {
            int spaceIndex = msg.indexOf(" ");
            if (spaceIndex < 0) {
                cq.sendPrivateMsg(userId, "wiki.biligame.com/blhx/", false);
                return MESSAGE_BLOCK;
            }
            String str1 = msg.substring(spaceIndex).trim();
            String str2;

            try {
                //将中文转换成百分号编码(URL中需要使用百分号编码)
                str2 = URLEncoder.encode(str1, "utf-8");
                cq.sendPrivateMsg(userId, "wiki.biligame.com/blhx/index.php?search=" + str2, false);
            } catch (UnsupportedEncodingException e) {
                cq.sendPrivateMsg(userId, "error：wiki", false);
                e.printStackTrace();
            }
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }

    // blhxwiki
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long groupId = event.getGroupId();

        if (msg.matches("^碧蓝wiki.*") || msg.matches("^blhxwiki.*") || msg.matches("^blwk.*")) {
            int spaceIndex = msg.indexOf(" ");
            if (spaceIndex < 0) {
                try {
                    cq.sendGroupMsg(groupId, "wiki.biligame.com/blhx/" + URLEncoder.encode("首页", "utf-8"), false);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    cq.sendGroupMsg(groupId, "error：wiki", false);
                }
                return MESSAGE_BLOCK;
            }
            String str1 = msg.substring(spaceIndex).trim();
            String str2;
            try {
                //将中文转换成百分号编码(URL中需要使用百分号编码)
                str2 = URLEncoder.encode(str1, "utf-8");
                cq.sendGroupMsg(groupId, "wiki.biligame.com/blhx/index.php?search=" + str2, false);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                cq.sendGroupMsg(groupId, "error：wiki", false);
            }
            return MESSAGE_BLOCK;
        }
        if (msg.matches("指挥[喵猫]一图榜")||msg.matches("[喵猫]榜")) {
            cq.sendGroupMsg(groupId, "紫猫一图榜：https://gitee.com/fnsflm/myPicbed/raw/master/20201229113233.png\n" +
                    "金猫一图榜：https://gitee.com/fnsflm/myPicbed/raw/master/20201229113515.png\n" +
                    "天赋一图榜：https://gitee.com/fnsflm/myPicbed/raw/master/20201229113632.png", false);
            cq.sendGroupMsg(groupId,CQCode.image("https://gitee.com/fnsflm/myPicbed/raw/master/20201229113233.png"),false);
            cq.sendGroupMsg(groupId,CQCode.image("https://gitee.com/fnsflm/myPicbed/raw/master/20201229113515.png"),false);
            cq.sendGroupMsg(groupId,CQCode.image("https://gitee.com/fnsflm/myPicbed/raw/master/20201229113632.png"),false);
        }
        if (msg.matches("^方舟wiki.*") || msg.matches("^mrfzwiki.*") || msg.matches("^fzwk.*")) {
            int spaceIndex = msg.indexOf(" ");
            if (spaceIndex < 0) {
                try {
                    cq.sendGroupMsg(groupId, "wiki.biligame.com/arknights/" + URLEncoder.encode("首页", "utf-8"), false);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    cq.sendGroupMsg(groupId, "error：wiki", false);
                }
                return MESSAGE_BLOCK;
            }
            String str1 = msg.substring(spaceIndex).trim();
            String str2;
            try {
                //将中文转换成百分号编码(URL中需要使用百分号编码)
                str2 = URLEncoder.encode(str1, "utf-8");
                cq.sendGroupMsg(groupId, "wiki.biligame.com/arknights/index.php?search=" + str2, false);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                cq.sendGroupMsg(groupId, "error：wiki", false);
            }
            return MESSAGE_BLOCK;
        }
        if (msg.matches("^pcrwiki.*") || msg.matches("^pcrwk.*")) {
            int spaceIndex = msg.indexOf(" ");
            if (spaceIndex < 0) {
                try {
                    cq.sendGroupMsg(groupId, "wiki.biligame.com/pcr/" + URLEncoder.encode("首页", "utf-8"), false);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    cq.sendGroupMsg(groupId, "error：wiki", false);
                }
                return MESSAGE_BLOCK;
            }
            String str1 = msg.substring(spaceIndex).trim();
            String str2;
            try {
                //将中文转换成百分号编码(URL中需要使用百分号编码)
                str2 = URLEncoder.encode(str1, "utf-8");
                cq.sendGroupMsg(groupId, "wiki.biligame.com/pcr/index.php?search=" + str2, false);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                cq.sendGroupMsg(groupId, "error：wiki", false);
            }
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }
}
