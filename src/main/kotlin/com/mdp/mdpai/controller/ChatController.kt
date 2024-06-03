package com.mdp.mdpai.controller

import org.springframework.ai.chat.model.ChatModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/chat")
class ChatController @Autowired constructor(
    private val chatModel: ChatModel
) {

    @GetMapping
    fun getChatResponse(
        @RequestParam("prompt") prompt: String
    ): String {
        val response = chatModel.call(prompt)
        return response;
    }

    @GetMapping("/stream")
    fun getStream(
        @RequestParam("prompt") prompt: String
    ): Flux<String> {
        val response = chatModel.stream(prompt)
        return response;
    }
}