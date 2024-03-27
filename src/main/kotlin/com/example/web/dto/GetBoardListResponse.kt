package com.example.web.dto

data class GetBoardListResponse(
    val page: Int,
    val size: Int,
    val totalElement: Long,
    val totalPages: Int,
    val boardIdList: List<Long>,
) {
}
