package com.example.web.dto

data class CreateBoardRequest(
    val title: String,
    val author: String,
    val password: String,
    val content: String
)