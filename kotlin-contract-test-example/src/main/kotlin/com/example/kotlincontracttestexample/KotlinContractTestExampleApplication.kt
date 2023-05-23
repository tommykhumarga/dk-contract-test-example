package com.example.kotlincontracttestexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class KotlinContractTestExampleApplication

fun main(args: Array<String>) {
    runApplication<KotlinContractTestExampleApplication>(*args)
}
