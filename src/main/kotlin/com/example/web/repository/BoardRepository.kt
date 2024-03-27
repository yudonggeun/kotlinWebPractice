package com.example.web.repository

import com.example.web.entity.Board
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface BoardRepository : JpaRepository<Board, Long> {

    @Query("select b.id from Board b")
    fun findList(pageable: Pageable): Page<Long>

    @Query("select b.password from Board b where b.id = :id")
    fun findPasswordById(@Param("id") id: Long): String?
}