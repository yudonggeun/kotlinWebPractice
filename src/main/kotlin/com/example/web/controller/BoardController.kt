package com.example.web.controller

import com.example.web.dto.BoardInfoResponse
import com.example.web.dto.CreateBoardRequest
import com.example.web.dto.GetBoardListResponse
import com.example.web.dto.UpdateBoardRequest
import com.example.web.service.BoardService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class BoardController(val boardService: BoardService) {
    @GetMapping(value = ["/board/{id}"])
    fun getBoardInfo(@PathVariable id: Long): BoardInfoResponse {
        return boardService.getBoardInfo(id)
    }
    @GetMapping("/board")
    fun getBoardList(
        @PageableDefault(sort = ["createdAt"], direction = Sort.Direction.DESC)
        pageable: Pageable,
    ): GetBoardListResponse {
        return boardService.getBoardList(pageable)
    }
    @PostMapping("/board")
    fun createBoard(@RequestBody request: CreateBoardRequest): BoardInfoResponse {
        return boardService.createBoard(request)
    }
    @PatchMapping("/board")
    fun updateBoard(@RequestBody request: UpdateBoardRequest): BoardInfoResponse {
        return boardService.update(request)
    }
    @DeleteMapping("/board/{id}")
    fun deleteBoard(@PathVariable id: Long, @RequestParam password: String): HttpEntity<*> {
        boardService.delete(id, password);
        return ResponseEntity.EMPTY;
    }
}