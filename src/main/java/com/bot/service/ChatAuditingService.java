package com.bot.service;

import com.bot.dto.Message;
import com.bot.dto.Question;
import com.bot.dto.Topic;
import com.bot.entity.ChatAuditing;
import com.bot.repository.ChatAuditingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatAuditingService {

    @Autowired
    private ChatAuditingRepository chatAuditingRepository;

    @Autowired
    private TopicQuestionService topicQuestionService;

    public void saveChatAudit(Map<String,Object> requestParams, String model){
        ChatAuditing auditing = new ChatAuditing();
        if(requestParams!= null){
            Long topicId = null;
            Long queId = null;
            if(requestParams.get("topicId") != null) {
                topicId = Long.parseLong(String.valueOf(requestParams.get("topicId")));
                if(topicId!= null)
                    auditing.setTopicId(topicId);
            }
            if(requestParams.get("questionId") != null) {
                queId = Long.parseLong(String.valueOf(requestParams.get("questionId")));
                if(queId!= null) {
                    auditing.setQuestionId(queId);
                    if(topicQuestionService.getQuestionDescById(queId) != null) {
                        auditing.setMessage(topicQuestionService.getQuestionDescById(queId));
                    }
                }
            }
        }
        auditing.setModel(model);
        ChatAuditing savedAudit = chatAuditingRepository.save(auditing);
        System.out.println(savedAudit);
    }
}
