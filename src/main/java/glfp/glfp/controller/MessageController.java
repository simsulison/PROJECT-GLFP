package glfp.glfp.controller;

import glfp.glfp.dto.MemberDto;
import glfp.glfp.model.MessageModel;
import glfp.glfp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final MemberService memberService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public MessageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable Long to, MessageModel message) {

        System.out.println("handling send msg: " + message + "to : "+ to);
        // to : memberId(Long)
        MemberDto result = memberService.getMember(to);

        //멤버가 존재하면
        if (result != null) {
            simpMessagingTemplate.convertAndSend("/topic/messages/"+to, message);
        }

    }



}
