package com.example.kotlincontracttestexample.web

import com.example.kotlincontracttestexample.common.BadRequestException
import com.example.kotlincontracttestexample.dto.ErrorWrapper
import com.example.kotlincontracttestexample.enums.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ServerWebInputException

@RestControllerAdvice
class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorWrapper> {
        return when (e) {
            is BadRequestException, is ServerWebInputException -> handleGeneric(e, HttpStatus.BAD_REQUEST, true)
            else -> handleGeneric(e, HttpStatus.INTERNAL_SERVER_ERROR, true)
        }
    }

    private fun handleGeneric(
        e: Exception,
        httpStatus: HttpStatus,
        genericMessage: Boolean = false
    ): ResponseEntity<ErrorWrapper> {
        val errorCode = if (httpStatus.is4xxClientError) ErrorCode.BAD_REQUEST else ErrorCode.INTERNAL_SERVER_ERROR
        val message = if (genericMessage) errorCode.errorMessage else e.message ?: errorCode.errorMessage

        return ResponseEntity(ErrorWrapper(message), httpStatus)
    }
}