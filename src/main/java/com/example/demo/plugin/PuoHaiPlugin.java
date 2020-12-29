package com.example.demo.plugin;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.event.message.CQPrivateMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;
import java.util.regex.*;

@Component
public class PuoHaiPlugin extends CQPlugin {
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        String msg = event.getMessage();
        long groupId = event.getGroupId();
        long userId = event.getUserId();
        //System.out.println(msg);
        if(msg.matches(".*迫害.*\\[CQ:at,qq=1501670812\\].*")||msg.matches(".*迫害.*柴.*郡.*天.*下.*第.*一.*")){
            pohai(cq,groupId,userId);
            return MESSAGE_BLOCK;
        }
        if(msg.matches(".*迫害.*\\[CQ:at,qq=1132492036\\].*")||msg.matches(".*迫害.*"+cq.getGroupMemberInfo(groupId,1132492036,false).getData().getCard()+".*")){
            cq.sendGroupMsg(groupId,"啊呜啊呜，好过分的nanodesu",false);
            return MESSAGE_BLOCK;
        }
        Pattern p = Pattern.compile("^迫害 \\[CQ:at,qq=.+\\]");
        Matcher m =p.matcher(msg);
        if(m.find()){
            String PHstr= m.group(0);
            //System.out.println(PHstr);
            if(PHstr!=null){
                long tmpid = Long.parseLong(PHstr.substring(13,PHstr.length()-1));
                pohai(cq,groupId,tmpid);
                return MESSAGE_BLOCK;
            }
        }
        if(msg.matches("^迫害 .*")){
            String s = msg.substring(3);
            if(s.length()==0){
                pohai(cq,groupId,userId);
            }else{
                String ss = "555，";
                for(char i:s.toCharArray()){
                    ss = ss + i + "——";
                }
                ss = ss + "倒——了——了——了——";
                cq.sendGroupMsg(groupId,ss,false);
            }
            return MESSAGE_BLOCK;
        }
        if(msg.contains("迫害")){
            pohai(cq,groupId,userId);
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }

    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        String msg = event.getMessage();
        System.out.println(msg);
        long userId = event.getUserId();
        if(Pattern.matches("^迫害 \\[CQ:at,qq=.+\\]", msg)){
            cq.sendPrivateMsg(userId,"ok",false);
//            String s = cq.getGroupMemberInfo(1067471239,userId,false).getData().getCard();
//            String ss = "";
//            for(char i:s.toCharArray()){
//                ss = ss + i + "--";
//            }
//            ss = ss + "倒--了--!";
//            System.out.println(ss);
            //System.out.println(CQCode.at(userId));
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }
    public void pohai(CoolQ cq,long groupId,long tmpid){
        String s = cq.getGroupMemberInfo(groupId,tmpid,false).getData().getCard();
        //System.out.println("123:"+s);
        if(s.length()==0){
            s = cq.getGroupMemberInfo(groupId,tmpid,false).getData().getNickname();
        }
        String ss = "555，";
        for(char i:s.toCharArray()){
            ss = ss + i + "——";
        }
        ss = ss + "倒——了——了——了——";
        //System.out.println(ss);
        cq.sendGroupMsg(groupId,ss,false);
    }
}
