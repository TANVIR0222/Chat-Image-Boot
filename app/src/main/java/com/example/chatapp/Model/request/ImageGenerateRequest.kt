package com.example.chatapp.Model.request


import com.google.gson.annotations.SerializedName

data class ImageGenerateRequest(
    @SerializedName("n")
    val n: Int?,
    @SerializedName("prompt")
    val prompt: String?,
    @SerializedName("size")
    val size: String?
)