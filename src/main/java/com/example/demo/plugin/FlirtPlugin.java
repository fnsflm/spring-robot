package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Component;

@Component
public class FlirtPlugin extends CQPlugin {
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();
        if(msg.equals("掀裙子")) {
            cq.sendGroupMsg(groupId, "啊呜啊呜，好害羞的nanodesu", false);
            return MESSAGE_BLOCK;
        }
        if(msg.equals("脱丝袜")) {
            cq.sendGroupMsg(groupId, "啊呜啊呜，好变态的nanodesu", false);
            return MESSAGE_BLOCK;
        }
        if(msg.equals("舔耳朵")) {
            cq.sendGroupMsg(groupId, "啊呜啊呜，好痒的nanodesu", false);
            return MESSAGE_BLOCK;
        }
        if(msg.equals("解胖次")) {
            cq.sendGroupMsg(groupId, "啊呜啊呜，再拉就要掉下来了nanodesu", false);
            return MESSAGE_BLOCK;
        }
        if(msg.matches(".*我.*好了,.*")){
            cq.sendGroupMsg(groupId, "不许好，憋回去", false);
        }
        return MESSAGE_IGNORE;
    }
}
