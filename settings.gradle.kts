rootProject.name = "armeria-grpc-example"

pluginManagement {

    val springBootVersion = "2.7.3"
    val springDependencyManagementVersion: String = "1.0.13.RELEASE"
    val kotlinVersion: String = "1.6.21"
    val protobufVersion = "0.8.15"

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion

        id("com.google.protobuf") version protobufVersion
    }
}

include("server")
include("client")
include("grpc-interface")