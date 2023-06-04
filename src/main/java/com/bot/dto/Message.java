package com.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class Message {

    private String role;
    private String content;

    public Message(String role, String content){
        this.role = role;
        if(content!= null) {
            this.content = content;
        }
    }
}
