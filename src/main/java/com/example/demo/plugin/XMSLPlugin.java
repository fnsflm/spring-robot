package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.util.regex.*;

@Component
public class XMSLPlugin extends CQPlugin {
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long userId = event.getUserId();
        long groupId = event.getGroupId();
        if (msg.matches(".*大佬太强了.*")) {
            cq.sendGroupMsg(groupId, CQCode.at(userId) + " 啊呜啊呜，羡慕大佬nanodesu", false);
            return MESSAGE_BLOCK;
        }
        if (Math.random() < 0.02) {
            //String[] responses = {" 啊呜啊呜，羡慕大佬nanodesu", " 太强了nanodesu"};
            //String response = responses[];
            if (Math.random() < 0.5)
                cq.sendGroupMsg(groupId, CQCode.at(userId) + " 啊呜啊呜，羡慕大佬nanodesu", false);
            else
                cq.sendGroupMsg(groupId, CQCode.at(userId) + " 太强了nanodesu", false);

            return MESSAGE_IGNORE;
        }
        if (msg.matches(".*不会.*有人.+吧.*")) {
            cq.sendGroupMsg(groupId, "不会吧？不会吧？", false);
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }
}
