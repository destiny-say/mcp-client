package com.mcp.client.web;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chat")
public class ChatCtrl {

    @Resource
    private ChatClient chatClient;

    @PostMapping(value = "/ai/answer", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generate(@RequestBody String connect) {
    	return chatClient.prompt(new Prompt(new SystemMessage("使用提供的工具"), new UserMessage(connect))).stream().content();
    }
    
    @PostMapping(value = "/ai/web", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> generateWeb(@RequestBody String connect) {
        String answer = chatClient.prompt(new Prompt(new SystemMessage("使用提供的工具"), new UserMessage(connect))).call().content();
        System.out.println(answer);
        return Flux.just(answer);
    }
    
    @PostMapping(value = "/ai/travel", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> travel(@RequestBody String connect) {
        String answer = chatClient.prompt(new Prompt(new SystemMessage("可以通过获取城市的经纬度再获取它的天气情况"), new UserMessage(connect))).call().content();
        System.out.println(answer);
        return Flux.just(answer);
//    	return chatClient.prompt(new Prompt(new SystemMessage("使用提供的工具"), new UserMessage(connect))).stream().content();
    }
}
