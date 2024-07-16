package com.mredust.chatrobot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Markdown消息
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarkdownMessage extends Message {
    private MarkdownContent markdown;
    
    
    public MarkdownMessage(String message) {
        setMsgtype("markdown");
        this.markdown = new MarkdownContent(message);
    }
    
    @Data
    public static class MarkdownContent {
        private String content;
        
        public MarkdownContent(String message) {
            this.content = message;
        }
    }
    
}
