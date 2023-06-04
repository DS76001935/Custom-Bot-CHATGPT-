package com.bot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_topic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "topic_id")
    private Long id;
    private String title;

    public Topic(com.bot.dto.Topic topicDto){
        if(topicDto.getIndex() != null)
            this.id = topicDto.getIndex();
        if(topicDto.getTitle() != null)
            this.title = topicDto.getTitle();
    }
}
