package com.bot.controller;

import com.bot.dto.*;
import com.bot.service.ChatAuditingService;
import com.bot.service.TopicQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class BotController {

    @Value("${openai.model}")
    private String model;

    @Value(("${openai.api.url}"))
    private String apiURL;

    @Autowired
    private RestTemplate template;

    @Autowired
    private ChatAuditingService chatAuditingService;

    @Autowired
    private TopicQuestionService topicQuestionService;

    @GetMapping("/chat")
    public String doChat(@RequestBody Map<String,Object> reqMap){
        BotRequest botReq = new BotRequest();
        BotResponse botRes = new BotResponse();
        if(reqMap.get("questionId") != null) {
            String queDesc = topicQuestionService.getQuestionDescById(Long.parseLong(String.valueOf(reqMap.get("questionId"))));
            if(queDesc!= null)
                botReq = new BotRequest(model, queDesc);
        }
        if(botReq != null)
            botRes = template.postForObject(apiURL, botReq, BotResponse.class);
        chatAuditingService.saveChatAudit(reqMap, model);
        if(botRes!= null)
            return verifyBotResponse(botRes.getChoices().get(0).getMessage().getContent());
        else
            return "!! Invalid Message !!";
    }

    public String verifyBotResponse(String response){
        if(response.toLowerCase().contains("sorry".toLowerCase())){
            return "Message passed against it's privacy consent!";
        }
        return response;
    }

    @PostMapping("/saveTopic")
    public ResponseEntity<?> saveTopic(@RequestBody Topic topicDto){
        com.bot.entity.Topic topicInstance = topicQuestionService.saveTopic(topicDto);
        return new ResponseEntity<Topic>(new Topic(topicInstance), HttpStatus.CREATED);
    }

    @PostMapping("/saveQuestion")
    public ResponseEntity<?> saveQuestion(@RequestBody Question questionDto){
        com.bot.entity.Question questionInstance = topicQuestionService.saveQuestion(questionDto);
        return new ResponseEntity<Question>(new Question(questionInstance), HttpStatus.CREATED);
    }
}
