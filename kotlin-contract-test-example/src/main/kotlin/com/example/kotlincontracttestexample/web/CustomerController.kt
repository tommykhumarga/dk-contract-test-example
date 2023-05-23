package com.example.kotlincontracttestexample.web

import com.example.kotlincontracttestexample.service.example.CustomerRequest
import com.example.kotlincontracttestexample.service.example.CustomerResponse
import com.example.kotlincontracttestexample.service.example.CustomerService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerController(private val customerService: CustomerService) {
    @GetMapping
    fun findAll(): List<CustomerResponse> {
        return customerService.findAll()
    }

    @PostMapping
    fun create(@Validated @RequestBody body: CustomerRequest): CustomerResponse {
        return customerService.create(body)
    }
}