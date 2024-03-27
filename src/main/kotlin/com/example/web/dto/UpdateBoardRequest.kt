package com.example.web.dto

data class UpdateBoardRequest(
    val id: Long,
    val title: String,
    val author: String,
    val password: String,
    val content: String
)