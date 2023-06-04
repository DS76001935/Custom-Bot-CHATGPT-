package com.bot.entity;

import com.bot.dto.Message;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_chat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "chat_id")
    private Long id;
    private Long questionId;
    private Long topicId;
    private String model;
    private String message;
    private LocalDateTime createdOn = LocalDateTime.now();
}
