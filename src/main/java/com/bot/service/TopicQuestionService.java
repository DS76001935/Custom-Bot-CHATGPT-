package com.bot.service;

import com.bot.entity.Question;
import com.bot.entity.Topic;
import com.bot.repository.QuestionRepository;
import com.bot.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicQuestionService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Topic saveTopic(com.bot.dto.Topic topicDto){
        Topic topicInstance = new Topic(topicDto);
        topicInstance = topicRepository.save(topicInstance);
        return topicInstance;
    }

    public Question saveQuestion(com.bot.dto.Question questionDto){
        Question questionInstance = new Question(questionDto);
        if(questionDto.getTopicId()!= null) {
            Optional<Topic> topicOp = topicRepository.findById(questionDto.getTopicId());
            if(topicOp.isPresent()){
                questionInstance.setTopic(topicOp.get());
            }
        }
        questionInstance = questionRepository.save(questionInstance);
        return questionInstance;
    }

    public String getQuestionDescById(Long questionId){
        Optional<Question> queOp = questionRepository.findById(questionId);
        if(queOp.isPresent())
            return queOp.get().getDescription();
        return null;
    }
}
