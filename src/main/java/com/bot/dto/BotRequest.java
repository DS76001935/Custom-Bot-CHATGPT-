package com.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class BotRequest {

    private List<Message> messages;
    private String model;

    public BotRequest(String model, String content){
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", content));
    }

}
