package com.spring.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by K on 2017/9/12.
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(Principal principal,String msg){
        if(principal.getName().equals("wy")){
            messagingTemplate.convertAndSendToUser("lll","/queue/singleChat",principal.getName()+"-Send:"+msg);
        }
        if(principal.getName().equals("lll")){
            messagingTemplate.convertAndSendToUser("wy","/queue/singleChat",principal.getName()+"-Send:"+msg);
        }
    }
}
