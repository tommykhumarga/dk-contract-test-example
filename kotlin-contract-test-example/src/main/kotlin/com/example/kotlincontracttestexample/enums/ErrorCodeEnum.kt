package com.example.kotlincontracttestexample.enums

import org.springframework.http.HttpStatus

enum class ErrorCode(val errorMessage: String, val statusCode: HttpStatus) {
    BAD_REQUEST("Bad request", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR)
}
