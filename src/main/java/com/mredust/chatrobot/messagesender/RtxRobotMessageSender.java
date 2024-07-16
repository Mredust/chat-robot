package com.mredust.chatrobot.messagesender;

import cn.hutool.json.JSONUtil;
import com.mredust.chatrobot.config.WebhookConfig;
import com.mredust.chatrobot.model.Message;
import com.mredust.chatrobot.model.TextMessage;
import lombok.Data;
import okhttp3.*;

import java.util.List;

/**
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
public class RtxRobotMessageSender implements MessageSender {
    
    private String webhook;
    
    public WebhookConfig webhookConfig;
    
    public RtxRobotMessageSender(String webhook) {
        this.webhook = webhook;
    }
    
    
    public void sendMessage(String message) throws Exception {
        sendMessage(message, null, null, false);
    }
    
    public void sendMessageAndAtAll(String message) throws Exception {
        sendMessage(message, null, null, true);
    }
    
    public void sendMessageAndAtByMentionList(String message, List<String> mentionedList) throws Exception {
        sendMessage(message, mentionedList, null, true);
    }
    
    public void sendMessageAndAtByMobileList(String message, List<String> mentionedMobileList) throws Exception {
        sendMessage(message, null, mentionedMobileList, true);
    }
    
    public void sendMessage(String message, List<String> mentionedList, List<String> mentionedMobileList, boolean isAtAll) throws Exception {
        TextMessage textMessage = new TextMessage(message, mentionedList, mentionedMobileList, isAtAll);
        sendMessage(textMessage);
    }
    
    @Override
    public void sendMessage(Message message) throws Exception {
        String webhook = this.webhook;
        String messageJsonObject = JSONUtil.toJsonStr(message);
        if (this.webhook == null || this.webhook.isEmpty()) {
            try {
                webhook = webhookConfig.getWebhook();
            } catch (Exception e) {
                throw new RuntimeException("webhook配置为空", e);
            }
        }
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), messageJsonObject);
        Request request = new Request.Builder().url(webhook).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("消息发送失败,响应码:" + response.code() + ",响应消息:" + response.message());
            }
        } catch (Exception e) {
            throw new Exception("消息发送失败", e);
        }
    }
}
