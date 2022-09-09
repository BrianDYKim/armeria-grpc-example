package com.example.client

import com.brian.grpcIfs.v1.user.RegisterUserRequest
import com.brian.grpcIfs.v1.user.RegisterUserResponse
import com.brian.grpcIfs.v1.user.UserServiceGrpcKt
import io.grpc.ManagedChannelBuilder
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController {

    companion object {
        val channel = ManagedChannelBuilder.forAddress("localhost", 8081)
            .usePlaintext()
            .build()

        val logger = LoggerFactory.getLogger(this::class.java)
    }

    @PostMapping
    suspend fun registerUser(
        @RequestParam("id") id: String,
        @RequestParam("password") password: String
    ): RegisterUserResponse {
        val userStub = UserServiceGrpcKt.UserServiceCoroutineStub(channel) // user stub을 생성

        logger.info(id)
        logger.info(password)

        val request = RegisterUserRequest
            .newBuilder()
            .setLoginId(id)
            .setPassword(password)
            .build()

        return userStub.registerUser(request)
    }
}