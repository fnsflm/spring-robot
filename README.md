# spring-robot

[toc]



完全基于[Spring-CQ](https://github.com/lz1998/spring-cq)的开发,具体问题可以[加群](https://jq.qq.com/?_wv=1027&k=5BKAROL)(群主是lz1998是Spring-CQ的开发者)

目前只私人使用,可参考代码自行开发

- 基于 ~~酷Q、cqhttp、~~mirai、go-cqhttp、SpringBoot、反向websocket 的 QQ 机器人框架

- 这是一个自定义spring-boot-starter，项目名暂时不改了

- 比起其他http框架，可以很方便地开多个QQ号，不需要额外配置cqhttp端口

- 这个README主要讲了大概的使用方法，详细API和EVENT看下面两个链接

- 详细API文档:https://github.com/lz1998/Spring-CQ/blob/demo/API.md

- 详细Event文档：https://github.com/lz1998/Spring-CQ/blob/demo/Event.md

- 新手推荐视频教程[旧]：https://www.bilibili.com/video/av89649630/  

- Kotlin也可以用：https://github.com/lz1998/Spring-CQ-Kotlin-Demo

- Groovy也可以用：https://github.com/lz1998/Spring-CQ-Groovy-Demo

- demo分支是例子，jar分支是maven仓库的spring-cq

## 开发环境
- IntelliJ IDEA Ultimate(学生认证免费)
- IntelliJ IDEA中的lombok插件，File->Settings->Plugins->搜索Lombok->Install->重启IDEA
- JDK IDEA自动安装，不需要自己装
- MAVEN IDEA自动安装，不需要自己装

## 导入maven依赖
```xml
    <dependency>
        <groupId>net.lz1998</groupId>
        <artifactId>spring-cq</artifactId>
        <version>4.14.1.1</version>
    </dependency>
```

推荐SpringBoot 2.1.8  
为了避免一些问题，可以直接下载demo修改

## cq转到mirai

写一个顶层插件

```java
@Component
public class Savebot extends CQPlugin {
    @Override
    public int onGroupMessage(CoolQ cq, CQGroupMessageEvent event) {
        event.setMessage(event.getRawMessage());
        return super.onGroupMessage(cq, event);
    }

    @Override
    public int onPrivateMessage(CoolQ cq, CQPrivateMessageEvent event) {
        event.setMessage(event.getRawMessage());
        return super.onPrivateMessage(cq, event);
    }
}
```

放在`application.yml`的plugin-list最前面


## 编写插件

1. 编写XXXPlugin，继承CQPlugin  
    ```java
   /**
    * 示例插件
    * 插件必须继承CQPlugin，上面要 @Component
    *
    * 添加事件：光标移动到类中，按 Ctrl+O 添加事件(讨论组消息、加群请求、加好友请求等)
    * 查看API参数类型：光标移动到方法括号中按Ctrl+P
    * 查看API说明：光标移动到方法括号中按Ctrl+Q
    */
   @Component
   public class DemoPlugin extends CQPlugin {
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
   
           if (msg.equals("hello")) {
               // 回复内容为 at发送者 + hi
               String result = CQCode.at(userId) + "hi";
   
               // 调用API发送消息
               cq.sendGroupMsg(groupId, result, false);
   
               // 不执行下一个插件
               return MESSAGE_BLOCK;
           }
   
           // 继续执行下一个插件
           return MESSAGE_IGNORE;
       }
   }
   ```

2. 配置resources/application.yml
    ```yml
    server:
      port: 8081 # 下面的cqhttp都是8081端口，可以自己改
    
    spring:
      cq:
        # 在这里配置各个功能执行顺序
        # 如果前一个功能返回MESSAGE_BLOCK，下一个功能不会被执行
        # 如果前一个功能返回MESSAGE_IGNORE，会继续执行下一个功能
        plugin-list:
          - com.example.demo.plugin.DemoPlugin
          - com.example.demo.plugin.TestPlugin
          - com.example.demo.plugin.HelloPlugin
    ```

## 运行spring-cq-client

先运行spring-cq-client

```bash
java -jarspring-cq-client.jar
```

会出现报错,但会出现一个conf文件夹,修改config.json中的qq账号密码

再次运行,即可登录


## 测试应用
1. 运行SpringCqApplication的main方法

## 打包应用
1. 使用maven打包应用
    ```bash
    mvn clean package
    ```
2. 在target目录下，spring-cq-0.0.1-SNAPSHOT.jar即为打包的jar

## 运行应用
1. 输入指令
    ```bash
    java -jar spring-cq-0.0.1-SNAPSHOT.jar
    ```
如果是Windows，并且不需要查看运行情况，可以直接双击jar文件运行，右下角托盘会出现小图标


