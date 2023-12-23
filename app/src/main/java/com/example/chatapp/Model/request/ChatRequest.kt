package com.example.chatapp.Model.request


import com.google.gson.annotations.SerializedName

data class ChatRequest(
    @SerializedName("max_tokens")
    val maxTokens: Int?,
    @SerializedName("model")
    val model: String?,
    @SerializedName("prompt")
    val prompt: String?,
    @SerializedName("temperature")
    val temperature: Double?
)