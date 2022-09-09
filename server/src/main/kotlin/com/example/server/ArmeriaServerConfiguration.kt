package com.example.server

import com.linecorp.armeria.common.grpc.GrpcSerializationFormats
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.grpc.GrpcService
import com.linecorp.armeria.server.logging.AccessLogWriter
import com.linecorp.armeria.server.logging.ContentPreviewingService
import com.linecorp.armeria.server.logging.LoggingService
import com.linecorp.armeria.spring.ArmeriaServerConfigurator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.nio.charset.StandardCharsets

// Armeria를 사용하기 위한 configuration
@Configuration
class ArmeriaServerConfiguration(
    val grpcUserService: GrpcUserService
) {

    @Bean
    fun armeriaServerConfigurator(): ArmeriaServerConfigurator? {
        return ArmeriaServerConfigurator { serverBuilder ->
            serverBuilder.decorator(LoggingService.newDecorator())
            serverBuilder.decorator(
                ContentPreviewingService.newDecorator(
                    Int.MAX_VALUE,
                    StandardCharsets.UTF_8
                )
            )
            serverBuilder.accessLogWriter(AccessLogWriter.combined(), false)
            serverBuilder.service(
                GrpcService.builder()
                    .addService(grpcUserService)
                    .supportedSerializationFormats(GrpcSerializationFormats.values())
                    .enableUnframedRequests(true)
                    .build()
            )
            serverBuilder.serviceUnder("/docs", DocService())
        }
    }
}