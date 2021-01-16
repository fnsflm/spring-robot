package com.example.demo.soutu;

import net.lz1998.cq.event.message.CQGroupMessageEvent;
import net.lz1998.cq.robot.CQPlugin;
import net.lz1998.cq.robot.CoolQ;
import net.lz1998.cq.utils.CQCode;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SoutuPlugin extends CQPlugin {

    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        Long userId = event.getUserId();
        Long groupId = event.getGroupId();
        String msg = event.getMessage();
        String sauApi = "21b4b15668347329323a04bb651709bbea0de3b6";
        String reg = "^搜图\\[CQ:image,file=.+,url=(.+)]";
        Matcher matcher = Pattern.compile(reg).matcher(msg);
        if (matcher.find()) {
            String imgurl = matcher.group(1);
            try {
                SauSearch sauSearch = new SauSearch(sauApi, imgurl);
                ArrayList<SoutuRes> picList = sauSearch.getLs();
                if (picList.size() > 0) {
                    for(SoutuRes soutuRes:picList){
                        cq.sendGroupMsg(groupId,"作者:"+soutuRes.getData().getMember_name()+
                                "\n标题:"+soutuRes.getData().getTitle()+
                                "\npid:"+soutuRes.getData().getPixiv_id()+
                                "\n相似度:"+soutuRes.getHeader().getSimilarity()+
                                "\n链接:"+soutuRes.getData().getExt_urls().toString()+
                                "\n结果数:"+picList.size()+"\n"
                                + CQCode.image(soutuRes.getHeader().getThumbnail()),false);
                    }
                } else {
                    cq.sendGroupMsg(groupId, "SauceNan: 没有找到合适的图片!", false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return MESSAGE_BLOCK;
        }
        // 没有什么用的小功能
        // 图片转链接
        reg = "^图转链\\[CQ:image,file=.+,url=(.+)]";
        matcher = Pattern.compile(reg).matcher(msg);
        if (matcher.find()) {
            String imgurl = matcher.group(1);
            try{
                Process p = Runtime.getRuntime().exec("picgo upload "+imgurl);
                InputStream is = p.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = null;
                String nextline;
                while((nextline = reader.readLine())!= null){
                    System.out.println(nextline);
                    line = nextline;
                }
//            System.out.println(line);
                cq.sendGroupMsg(groupId,line,false);
            }catch(Exception e){
                e.printStackTrace();
            }
            return MESSAGE_BLOCK;
        }
        return MESSAGE_IGNORE;
    }
}
