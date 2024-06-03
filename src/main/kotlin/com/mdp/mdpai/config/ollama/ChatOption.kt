package com.mdp.mdpai.config.ollama

import org.springframework.boot.context.properties.NestedConfigurationProperty

class ChatOption(
) {
    var enabled: Boolean = true

    @NestedConfigurationProperty
    var options: Options = Options()

    override fun toString(): String {
        return "ChatOption(enabled=$enabled, options=$options)"
    }
}
