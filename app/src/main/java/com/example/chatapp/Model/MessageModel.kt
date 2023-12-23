package com.example.chatapp.Model

data class MessageModel(
    var isUser:Boolean,
    var isImage:Boolean,
    var message: String?
)