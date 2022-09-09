package com.example.server

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class UserController {

    companion object {
        val logger = LoggerFactory.getLogger(this::class.java)
    }
}