package com.mredust.chatrobot.messagesender;


import com.mredust.chatrobot.model.Message;

/**
 * 消息发送器
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
public interface MessageSender {
    
    void sendMessage(Message message) throws Exception;
}
