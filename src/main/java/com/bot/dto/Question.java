package com.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private Long index;
    private String description;
    private String type;
    private Long topicId;

    public Question(String desc){
        this.description = desc;
    }

    public Question(com.bot.entity.Question question){
        if(question.getId()!= null)
            this.index = question.getId();
        this.description = question.getDescription();
        this.type = question.getType();
        if(question.getTopic() != null && question.getTopic().getId() != null)
            this.topicId = question.getTopic().getId();
    }
}
