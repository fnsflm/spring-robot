package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

@Component
public class HelpPlugin extends CQPlugin {
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        if (msg.equals("哈牛帮助") || msg.equals("羽入帮助")) {
            cq.sendGroupMsg(groupId, "1.输入“哈牛帮助”或“羽入帮助”获取帮助信息\n" +
                    "2.打招呼功能回复“打招呼帮助”\n" +
                    "3.碧蓝航线wiki搜索功能帮助回复“wiki帮助”\n" +
                    "4.调戏功能回复“调戏帮助”\n" +
                    "5.提醒功能回复“提醒帮助”\n" +
                    "6.复读功能回复“复读帮助”\n" +
                    "7.b站动态功能回复“b站动态帮助”\n" +
                    "8.其它功能回复“其它帮助”", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("打招呼帮助")) {
            cq.sendGroupMsg(groupId, "1.命令：hi或hello，和羽入打招呼\n" +
                    "2.命令：at羽入+“自我介绍”，羽入做自我介绍\n" +
                    "3.命令：角色歌，羽入角色歌\n" +
                    "4.喵", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("wiki帮助")) {
            cq.sendGroupMsg(groupId, "命令：“碧蓝wiki”或“blhxwiki”或“blwk”+空格+搜索的内容\n可在碧蓝航线wiki搜索内容，若不加空格或者只有单独的上面三个，将返回碧蓝wiki首页链接nanodesu", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("调戏帮助") || (msg.contains(CQCode.at(1132492036L)) && msg.contains("调戏"))) {
            cq.sendGroupMsg(groupId, "啊呜啊呜，不要欺负人家nanodesu", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("提醒帮助")) {
            cq.sendGroupMsg(groupId, "在下会在每天的0点、12点、18点提醒演习，还有提醒21点开始的夜间委托和23点提醒打每日任务和困难关卡nanodesu", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("复读帮助")) {
            cq.sendGroupMsg(groupId, "每有两次相同的消息在下就会复读一次nanodesu", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("b站动态帮助")) {
            cq.sendGroupMsg(groupId, "1.命令：bzdt+空格+用户名，获取该up主的最新动态\n" +
                    "2.命令：bzdt+空格+用户名+空格+数字，获取该up主的第几条动态（从0开始计）\n" +
                    "3.命令：addbzdt+空格+用户名+空格+uid，添加该up主\n" +
                    "4.能实时获取被添加过的up主的动态\n" +
                    "注：其中bzdt是b站动态的缩写", false);
            return MESSAGE_BLOCK;
        }
        if (msg.equals("其它帮助")) {
            cq.sendGroupMsg(groupId, "1.命令：壁纸，随机壁纸链接\n" +
                    "2.计算器功能：cal+空格+表达式，返回运算结果\n" +
                    "3.命令：今日人品，返回0~99的整数，表示今日人品", false);
            return MESSAGE_BLOCK;
        }

        return MESSAGE_IGNORE;
    }
}
