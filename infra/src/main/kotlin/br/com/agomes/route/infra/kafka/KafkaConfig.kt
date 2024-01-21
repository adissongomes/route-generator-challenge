package br.com.agomes.route.infra.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaConfig {

    @Bean
    fun consumerFactory(properties: KafkaProperties, mapper: ObjectMapper): ConsumerFactory<String, *> {
        val jsonDeserializer = JsonDeserializer<Any>(mapper)
        return DefaultKafkaConsumerFactory(
            properties.buildConsumerProperties(null),
            StringDeserializer(),
            jsonDeserializer
        )
    }
}
