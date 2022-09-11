package com.example.kotlinwebsocket.domain

data class Message(
    val senderName: String?,
    val receiverName: String?,
    val message: String?,
    val date: String?,
    val status: Status?,
)
