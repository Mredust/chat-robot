package com.mredust.chatrobot.config;

import com.mredust.chatrobot.messagesender.RtxRobotMessageSender;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Webhook配置类
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Configuration
@ConfigurationProperties(prefix = "chat-robot")
@Component
@Data
public class WebhookConfig {
    
    private String webhook;
    
    @Bean
    public RtxRobotMessageSender rtxRobotMessageSender() {
        return new RtxRobotMessageSender(webhook);
    }
    
}
