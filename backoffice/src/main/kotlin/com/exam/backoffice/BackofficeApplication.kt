package com.exam.backoffice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaRepositories
class BackofficeApplication

fun main(args: Array<String>) {
    runApplication<BackofficeApplication>(*args)
}
