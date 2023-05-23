package com.example.kotlincontracttestexample.service.example

import javax.validation.constraints.NotBlank

data class CustomerRequest(
    @get:NotBlank val firstName: String,
    @get:NotBlank val lastName: String,
    @get:NotBlank val email: String
)

data class CustomerResponse(
    val name: String,
    val email: String
)
