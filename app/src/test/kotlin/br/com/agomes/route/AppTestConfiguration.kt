package br.com.agomes.route

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.test.context.TestConfiguration

@TestConfiguration
@EnableAutoConfiguration(exclude = [KafkaAutoConfiguration::class])
class AppTestConfiguration
