package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import org.springframework.stereotype.Component;

@Component
public class ErrCorrectPlugin extends CQPlugin {
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        Long groupId = event.getGroupId();
        if (msg.equals("草")) {
            cq.sendGroupMsg(groupId, "草：一种植物", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("艹")) {
            cq.sendGroupMsg(groupId, "艹：一个部首", false);
            return MESSAGE_BLOCK;
        }
        if (msg.matches("[Cc][Aa][Oo]")) {
            cq.sendGroupMsg(groupId, "CaO：一种化合物", false);
        }
        if (msg.equals("日")) {
            cq.sendGroupMsg(groupId, "日：一个天体", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("操")) {
            cq.sendGroupMsg(groupId, "操：用手拿着，握持", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("卧槽")) {
            cq.sendGroupMsg(groupId, "卧槽：五指卧槽马，象棋术语简称", false);
            return MESSAGE_BLOCK;
        }
        if (msg.contains("凌波")) {
            cq.sendGroupMsg(groupId, "绫波！", false);
            return MESSAGE_BLOCK;
        }
        if (msg.contains("乌海")) {
            cq.sendGroupMsg(groupId, "鸟海！", false);
            return MESSAGE_BLOCK;
        }
        if (msg.contains("赤诚")) {
            cq.sendGroupMsg(groupId, "赤城！", false);
            return MESSAGE_BLOCK;
        }
        if (msg.contains("刺鳍")) {
            cq.sendGroupMsg(groupId, "棘鳍！", false);
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }
}
