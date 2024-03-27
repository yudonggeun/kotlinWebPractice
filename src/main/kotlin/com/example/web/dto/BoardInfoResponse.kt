package com.example.web.dto

import com.example.web.entity.Board

data class BoardInfoResponse(
    val id: Long,
    val title: String,
    val author: String,
    val password: String,
    val content: String,
)

fun Board.toResponse(): BoardInfoResponse{
    return BoardInfoResponse(
        this.id!!,
        this.title,
        this.author,
        this.password,
        this.content
    )
}
