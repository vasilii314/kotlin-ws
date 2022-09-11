package com.example.kotlinwebsocket.controller

import com.example.kotlinwebsocket.domain.Message
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/message")
class MessageController(val simpMessagingTemplate: SimpMessagingTemplate) {

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    fun receivePublicMessage(@Payload message: Message): Message = message

    @MessageMapping("/private-message")
    fun receivePrivateMessage(@Payload message: Message): Message {
        message.receiverName?.let { simpMessagingTemplate.convertAndSendToUser(it, "/private", message) }
        return message
    }
}