package com.mdp.mdpai.config.ollama

class Options {

    var model: String = "mistral"
    var temperature: Float = 0.5f

    override fun toString(): String {
        return "Options(model='$model', temperature=$temperature)"
    }
}