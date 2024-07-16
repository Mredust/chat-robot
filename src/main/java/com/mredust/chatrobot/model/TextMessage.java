package com.mredust.chatrobot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 文本消息
 *
 * @author <a href="https://github.com/Mredust">Mredust</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TextMessage extends Message {
    private TextContent text;
    
    public TextMessage(String message) {
        this(message, null, null, false);
    }
    
    public TextMessage(String message, List<String> mentionedList, List<String> mentionedMobileList, Boolean isAt) {
        setMsgtype("text");
        this.text = new TextContent(message, mentionedList, mentionedMobileList, isAt);
    }
    
    @Data
    public static class TextContent {
        private String content;
        private List<String> mentioned_list;
        private List<String> mentioned_mobile_list;
        
        public TextContent(String message, List<String> mentionedList, List<String> mentionedMobileList, Boolean isAt) {
            this.content = message;
            if (Boolean.TRUE.equals(isAt)) {
                if (mentionedList != null && !mentionedList.isEmpty()) {
                    this.mentioned_list = new ArrayList<>(mentionedList);
                } else if (mentionedMobileList != null && !mentionedMobileList.isEmpty()) {
                    this.mentioned_mobile_list = new ArrayList<>(mentionedMobileList);
                } else {
                    this.mentioned_list = Collections.singletonList("@all");
                }
            }
        }
    }
    
}
