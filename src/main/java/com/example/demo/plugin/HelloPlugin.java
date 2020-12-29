package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class HelloPlugin extends CQPlugin {
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();
        if (msg.equals("hello") || msg.equals(CQCode.at(1132492036L) + "hello")) {
            // 回复内容为 at发送者 + hi
            String result = CQCode.at(userId) + "hi";

            // 调用API发送消息
            cq.sendGroupMsg(groupId, result, false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        if (msg.equals("hi") || msg.equals(CQCode.at(1132492036L) + "hi")) {
            // 回复内容为 at发送者 + hi
            String result = CQCode.at(userId) + "hello";

            // 调用API发送消息
            cq.sendGroupMsg(groupId, result, false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        if (msg.equals("哈牛") || msg.equals("羽入") || msg.equals("nanodesu")) {
            // 回复内容为 at发送者 + hi
            String result = "nanodesu";

            // 调用API发送消息
            cq.sendGroupMsg(groupId, result, false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        if (msg.contains(CQCode.at(1132492036L)) && msg.matches(".*介绍.*")) {
            cq.sendGroupMsg(groupId, "啊呜啊呜，在下是雏见泽的神明大人，古手羽入nanodesu，喜欢吃甜食和豆腐，讨厌吃辣和喝酒nanodesu。愚钝的在下只能为大家提供些许便捷和微不足道的娱乐，不好意思nanodesu，啊呜啊呜", false);
            cq.sendGroupMsg(groupId, "详细介绍：https://zh.moegirl.org/%E7%BE%BD%E5%85%A5#", false);
            cq.sendGroupMsg(groupId, "角色歌：https://www.bilibili.com/video/BV1cJ411473q", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("角色歌")) {
            cq.sendGroupMsg(groupId, "https://www.bilibili.com/video/BV1cJ411473q", false);
            return MESSAGE_BLOCK;
        }
        if (msg.contains(CQCode.at(1132492036L)) && (msg.contains("爬") || msg.contains("爪巴"))) {
            cq.sendGroupMsg(groupId, "啊呜啊呜，十分抱歉nanodesu，", false);
        }
        if (msg.equals(CQCode.at(1132492036L))) {
            cq.sendGroupMsg(groupId, "nanodesu", false);
        }
        // 喵
        String pattern = ".*喵.*";
        if (Pattern.matches(pattern, msg)) {
            cq.sendGroupMsg(groupId, "喵", false);
        }
        return MESSAGE_IGNORE;
    }
}
