package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BlackListPlugin extends CQPlugin {
    Long blackList[] = {2425497030L};

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        Long userId = event.getUserId();
        if (Arrays.asList(blackList).contains(userId))
            return MESSAGE_BLOCK;
        else
            return MESSAGE_IGNORE;
    }
}
