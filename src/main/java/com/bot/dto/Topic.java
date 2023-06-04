package com.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    private Long index;
    private String title;

    public Topic(String title) {
        this.title = title;
    }

    public Topic(com.bot.entity.Topic topic){
        if(topic.getId()!= null)
            this.index = topic.getId();
        this.title = topic.getTitle();
    }
}
