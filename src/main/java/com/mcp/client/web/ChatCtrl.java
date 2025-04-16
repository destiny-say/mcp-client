package com.mcp.client.web;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
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

    // public ChatCtrl(ChatClient.Builder chatClientBuilder,
    // ChatMemory chatMemory,
    // ToolCallbackProvider tools) {
    // this.chatClient = chatClientBuilder
    // // .defaultAdvisors(new PromptChatMemoryAdvisor(chatMemory))
    // // .defaultTools(dateTimeService2)
    // .defaultTools(tools)
    // .build();
    // }

    @PostMapping(value = "/ai/answer", produces = "application/json;charset=UTF-8")
    public Flux<String> generate(@RequestBody String connect) {
        String answer = chatClient.prompt(new Prompt(new SystemMessage("使用提供的工具"), new UserMessage(connect))).call().content();
        System.out.println(answer);
        Map<String, Object> map = Map.of("answer", answer);
        return Flux.just(answer);
    }
}
