package cat.udl.eps.entsoftarch.webingogeiapi.websocket.controller;

import cat.udl.eps.entsoftarch.webingogeiapi.websocket.models.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@EnableScheduling
@Controller
public class ChatController {

    private final SimpMessagingTemplate template;

    @Autowired
    ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        this.template.convertAndSend("/topic/public", "adduser");
        return chatMessage;
    }

    @MessageMapping("/send/message")
    public void onReceivedMessage(String message) {
        this.template.convertAndSend("/topic/public", "onreceive");
    }

    @Scheduled(fixedRate = 5000)
    public void greeting() {
        this.template.convertAndSend("/topic/public", "Hello");
    }

}
