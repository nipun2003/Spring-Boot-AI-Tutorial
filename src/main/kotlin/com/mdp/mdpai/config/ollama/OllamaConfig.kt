package com.mdp.mdpai.config.ollama

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.ai.chat.model.ChatModel
import org.springframework.ai.ollama.OllamaChatModel
import org.springframework.ai.ollama.api.OllamaApi
import org.springframework.ai.ollama.api.OllamaOptions
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.NestedConfigurationProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("spring.ai.ollama")
class OllamaConfig {
    private val logger: Logger = LoggerFactory.getLogger(OllamaConfig::class.java)
    var baseUrl: String = "http://127.0.0.1:11453"
        set(value) = run { field = value }

    @NestedConfigurationProperty
    var chat: ChatOption = ChatOption()

    override fun toString(): String {
        return "OllamaConfig(baseUrl='$baseUrl', chat=$chat)"
    }

    @Bean
    fun provideChatModel(): ChatModel {
        logger.info("Creating OllamaChatModel with baseUrl: $baseUrl")
        val ollamaApi = OllamaApi(this.baseUrl)
        val chatModel = OllamaChatModel(
            ollamaApi,
            OllamaOptions.create()
                .withModel(this.chat.options.model)
                .withTemperature(this.chat.options.temperature)
        )
        return chatModel
    }
}