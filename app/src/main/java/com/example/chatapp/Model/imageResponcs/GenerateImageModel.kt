package com.example.chatapp.Model.imageResponcs


import com.google.gson.annotations.SerializedName

data class GenerateImageModel(
    @SerializedName("created")
    val created: Int?,
    @SerializedName("data")
    val `data`: List<Data>
) {
    data class Data(
        @SerializedName("url")
        val url: String?
    )
}