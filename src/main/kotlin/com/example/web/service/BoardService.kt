package com.example.web.service

import com.example.web.dto.*
import com.example.web.entity.Board
import com.example.web.repository.BoardRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
@Transactional
class BoardService(
    val boardRepository: BoardRepository,
) {
    fun createBoard(request: CreateBoardRequest): BoardInfoResponse {
        val board = boardRepository.save(
            Board(
                title = request.title,
                author = request.author,
                password = request.password,
                content = request.content
            )
        )

        return board.toResponse()
    }

    @Transactional(readOnly = true)
    fun getBoardInfo(id: Long): BoardInfoResponse {
        val board = boardRepository.findById(id)
            .orElseThrow { NotFoundException() }

        return board.toResponse();
    }

    @Transactional(readOnly = true)
    fun getBoardList(pageable: Pageable): GetBoardListResponse {
        val pages = boardRepository.findList(pageable);

        if (pages.isEmpty) throw NotFoundException()

        return GetBoardListResponse(
            pageable.pageNumber,
            pages.size,
            pages.totalElements,
            pages.totalPages,
            pages.content
        )
    }

    fun update(request: UpdateBoardRequest): BoardInfoResponse {
        val board = boardRepository.findById(request.id)
            .orElseThrow { NotFoundException() }

        checkAuth(board.password, request.password)

        board.update(request.title, request.author, request.content)
        return board.toResponse()
    }

    fun delete(id: Long, password:String) {
        val boardPassword = boardRepository.findPasswordById(id) ?: throw NotFoundException()
        checkAuth(password, boardPassword)
        boardRepository.deleteById(id)
    }

    private fun checkAuth(password: String, boardPassword: String) {
        if (boardPassword != password) {
            throw RuntimeException()
        }
    }
}