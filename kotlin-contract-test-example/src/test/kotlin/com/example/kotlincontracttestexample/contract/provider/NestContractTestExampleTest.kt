package com.example.kotlincontracttestexample.contract.provider

import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Consumer
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import com.example.kotlincontracttestexample.common.BadRequestException
import com.example.kotlincontracttestexample.enums.ErrorCode
import com.example.kotlincontracttestexample.service.example.CustomerRequest
import com.example.kotlincontracttestexample.service.example.CustomerResponse
import com.example.kotlincontracttestexample.web.CustomerController
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnabledIfEnvironmentVariable(named = "PACT_VERIFIER_PUBLISH", matches = ".*")
@Provider("kotlin-contract-test-example")
@Consumer("nest-contract-test-example")
@PactBroker(host = "localhost", port = "9292")
class NestContractTestExampleTest {
    @MockBean
    private lateinit var customerController: CustomerController

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }

    @BeforeEach
    fun before(context: PactVerificationContext) {
        context.target = HttpTestTarget("localhost", 8080, "/")
    }

    @State("bad request")
    fun `should respond with bad request`() {
        Mockito.`when`(customerController.create(CustomerRequest("John", "Doe", "johndoe@example.com")))
            .thenThrow(BadRequestException(ErrorCode.BAD_REQUEST))
    }

    @State("normal")
    fun `should respond newly created customer`() {
        val response = CustomerResponse("John Doe", "johndoe@example.com")

        Mockito.`when`(customerController.create(CustomerRequest("John", "Doe", "johndoe@example.com")))
            .thenReturn(response)
    }
}