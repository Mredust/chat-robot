package com.mredust.chatrobot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImageMessage extends Message {
    private ImageContent image;
    
    public ImageMessage(File imageFile) {
        setMsgtype("image");
        this.image = new ImageContent(imageFile);
    }
    
    @Data
    public static class ImageContent {
        private String base64;
        private String md5;
        
        public ImageContent(File imageFile) {
            this.base64 = encodeFileToBase64(imageFile);
            this.md5 = calculateMD5(imageFile);
        }
        
        private static String encodeFileToBase64(File file) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] bytes = new byte[(int) file.length()];
                int bytesRead = fis.read(bytes);
                if (bytesRead == -1) {
                    throw new IOException("File is empty: " + file.getName());
                }
                return Base64.getEncoder().encodeToString(bytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        private static String calculateMD5(File file) {
            try (FileInputStream fis = new FileInputStream(file)) {
                return DigestUtils.md5DigestAsHex(fis);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
