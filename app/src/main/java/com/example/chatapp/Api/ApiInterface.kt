package com.example.chatapp.Api

import com.example.chatapp.Model.Chate.ChatModel
import com.example.chatapp.Model.imageResponcs.GenerateImageModel
import com.example.chatapp.Model.request.ChatRequest
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {


    @POST("/v1/completions")
    suspend fun getChat(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") authorization :String,
        @Body request: RequestBody
    ):ChatModel


    @POST("/v1/images/generations")
    suspend fun generatesImage(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") authorization :String,
        @Body request: RequestBody
    ):GenerateImageModel


}