package com.mcp.client;

import org.springframework.ai.autoconfigure.mcp.client.SseHttpClientTransportAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {SseHttpClientTransportAutoConfiguration.class})
public class McpClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(McpClientApplication.class, args);
	}

}
