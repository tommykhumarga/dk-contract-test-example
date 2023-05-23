package com.example.kotlincontracttestexample.service.example

import org.springframework.stereotype.Component

@Component
class CustomerService {
    fun create(request: CustomerRequest): CustomerResponse {
        return CustomerResponse("${request.firstName} ${request.lastName}", request.email)
    }

    fun findAll(): List<CustomerResponse> {
        return listOf(
            CustomerResponse("John Doe", "johndoe@example.com"),
            CustomerResponse("Jane Doe", "janedoe@example.com")
        )
    }
}