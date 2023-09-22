package com.exam.front

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ConfigurationPropertiesScan
@EntityScan(basePackages = ["domain"])
@EnableJpaRepositories(basePackages = ["domain"])
class FrontApplication

fun main(args: Array<String>) {
    runApplication<FrontApplication>(*args)
}
