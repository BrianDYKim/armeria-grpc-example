package com.example.server

import com.brian.grpcIfs.v1.user.RegisterUserRequest
import com.brian.grpcIfs.v1.user.RegisterUserResponse
import com.brian.grpcIfs.v1.user.UserServiceGrpcKt
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GrpcUserService: UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    companion object {
        val logger = LoggerFactory.getLogger(this::class.java)
    }

    override suspend fun registerUser(request: RegisterUserRequest): RegisterUserResponse {
        logger.info(request.loginId)

        return RegisterUserResponse.newBuilder()
            .setLoginId(request.loginId)
            .build()
    }
}