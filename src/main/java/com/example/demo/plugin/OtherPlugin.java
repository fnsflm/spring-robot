package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.*;


/**
 * 示例插件
 * 插件必须继承CQPlugin，上面要 @Component
 * <p>
 * 添加事件：光标移动到类中，按 Ctrl+O 添加事件(讨论组消息、加群请求、加好友请求等)
 * 查看API参数类型：光标移动到方法括号中按Ctrl+P
 * 查看API说明：光标移动到方法括号中按Ctrl+Q
 */
@Component
public class OtherPlugin extends CQPlugin {
    /**
     * 收到私聊消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送私聊消息 sendPrivateMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        // 获取 发送者QQ 和 消息内容
        long userId = event.getUserId();
        String msg = event.getMessage();

        if (msg.equals("hi")) {
            // 调用API发送hello
            cq.sendPrivateMsg(userId, "hello", false);

            // 不执行下一个插件
            return MESSAGE_BLOCK;
        }
        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }


    /**
     * 收到群消息时会调用这个方法
     *
     * @param cq    机器人对象，用于调用API，例如发送群消息 sendGroupMsg
     * @param event 事件对象，用于获取消息内容、群号、发送者QQ等
     * @return 是否继续调用下一个插件，IGNORE表示继续，BLOCK表示不继续
     */
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        // 获取 消息内容 群号 发送者QQ
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();

        if (msg.matches("壁纸\\d*")) {
            int count = 1;
            if (msg.length() > 2) {
                count = Integer.parseInt(msg.substring(2));
            }
            for (int i = 0; i < count && i < 20; i++) {
//            cq.sendGroupMsg(groupId, "https://api.ixiaowai.cn/api/api.php", false);
                cq.sendGroupMsg(groupId, CQCode.image("https://api.ixiaowai.cn/api/api.php"), false);
            }
            return MESSAGE_BLOCK;
        }
        if (msg.contains("cal")) {
            String s = msg.substring(msg.indexOf(" ") + 1);
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            try {
                cq.sendGroupMsg(groupId, "result: " + engine.eval(s), false);
            } catch (Exception e) {
                e.printStackTrace();
                cq.sendGroupMsg(groupId, "error：计算", false);
            }
        }
        if (msg.matches("^[pP]站 .+")) {
            String ss[] = msg.split(" ");
            boolean flag = false;
            String s = "https://pixiv.cat/" + ss[1] + ".jpg";
            flag = try_to_visit(s);
            if (flag) {
                cq.sendGroupMsg(groupId, "https://pixiv.cat/" + ss[1] + ".jpg", false);
                cq.sendGroupMsg(groupId, CQCode.image("https://pixiv.cat/" + ss[1] + ".jpg"), false);
                return MESSAGE_BLOCK;
            }
            s = "https://pixiv.cat/" + ss[1] + "-1.jpg";
            flag = try_to_visit(s);
            if (flag) {
                cq.sendGroupMsg(groupId, "https://pixiv.cat/" + ss[1] + "-1.jpg", false);
                cq.sendGroupMsg(groupId, CQCode.image("https://pixiv.cat/" + ss[1] + "-1.jpg"), false);
                return MESSAGE_BLOCK;
            }
            cq.sendGroupMsg(groupId, "error：p站", false);
            return MESSAGE_BLOCK;
        }
        if (msg.contains("https://pixiv.net/")) {
            String pidurl = msg.split(" ")[0];
            String pid = pidurl.substring(20);
            if (try_to_visit("https://pixiv.cat/" + pid + ".jpg")) {
                cq.sendGroupMsg(groupId, "https://pixiv.cat/" + pid + ".jpg", false);
                return MESSAGE_BLOCK;
            }
            if (try_to_visit("https://pixiv.cat/" + pid + "-1.jpg")) {
                cq.sendGroupMsg(groupId, "https://pixiv.cat/" + pid + "-1.jpg", false);
                return MESSAGE_BLOCK;
            }
        }
        // 继续执行下一个插件
        return MESSAGE_IGNORE;
    }

    public boolean try_to_visit(String s) {
        try {
            URL url = new URL(s);
            //BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            URLConnection con = url.openConnection();
            con.addRequestProperty("User-agent", "Mozilla/4.0");
            InputStream in = con.getInputStream();
        } catch (Exception e) {
            System.out.println("error:" + s);
            e.printStackTrace();
            //e.printStackTrace();
            return false;
        }
        return true;
    }
}
