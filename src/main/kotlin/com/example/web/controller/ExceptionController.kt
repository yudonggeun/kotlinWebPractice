package com.example.web.controller

import com.example.web.dto.FailResponse
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): FailResponse{
        e.printStackTrace()
        return FailResponse("fail");
    }

    @ExceptionHandler(value = [NotFoundException::class])
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun handleNotFound(e: NotFoundException): FailResponse {
        e.printStackTrace()
        return FailResponse("not found")
    }
}