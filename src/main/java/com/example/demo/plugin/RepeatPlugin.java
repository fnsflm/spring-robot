package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

@Component
public class RepeatPlugin extends CQPlugin {
    String lastMsg;
    int count = 0;

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        if (msg.equals(lastMsg)) {
            count++;
            if (count > 1) {
                cq.sendGroupMsg(groupId, lastMsg, false);
                count = 0;
                return MESSAGE_IGNORE;
            }
        } else {
            count = 1;
            lastMsg = msg;
        }
        return MESSAGE_IGNORE;
    }
}
