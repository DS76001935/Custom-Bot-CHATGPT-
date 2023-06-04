package com.bot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tbl_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "question_id")
    private Long id;
    private String description;
    private String type;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Question(com.bot.dto.Question questionDto){
        if(questionDto.getIndex() != null)
            this.id = questionDto.getIndex();
        if(questionDto.getDescription()!= null)
            this.description = questionDto.getDescription();
        this.type = questionDto.getType();
    }

}
