package com.example.kotlincontracttestexample.common

import com.example.kotlincontracttestexample.enums.ErrorCode

class BadRequestException(val errorCode: ErrorCode) : RuntimeException(errorCode.errorMessage)