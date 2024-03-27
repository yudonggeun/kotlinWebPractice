package com.example.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class WebApplication

fun main(args: Array<String>) {
	runApplication<WebApplication>(*args)
}
