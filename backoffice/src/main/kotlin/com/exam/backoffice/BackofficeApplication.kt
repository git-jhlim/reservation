package com.exam.backoffice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@ConfigurationPropertiesScan
@EntityScan(basePackages = ["domain"])
@EnableJpaRepositories(basePackages = ["domain"])
@EnableScheduling
class BackofficeApplication

fun main(args: Array<String>) {
    runApplication<BackofficeApplication>(*args)
}
