load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS")
load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS")


def import_maven_repos():
    JAVA_GRPC_VERSION = "1.30.0"
    maven_install(
        artifacts = [
            # gPRC Java Lib
            "io.grpc:grpc-netty-shaded:" + JAVA_GRPC_VERSION,
            "io.grpc:grpc-protobuf:" + JAVA_GRPC_VERSION,
            "io.grpc:grpc-stub:" + JAVA_GRPC_VERSION,
            "io.grpc:grpc-api:" + JAVA_GRPC_VERSION,
            # docker client in java
            "com.github.docker-java:docker-java-core:3.2.5",
            "com.github.docker-java:docker-java-api:3.2.5",
            "com.github.docker-java:docker-java-transport-httpclient5:3.2.5",
            "com.github.docker-java:docker-java-transport:3.2.5",
        ] + IO_GRPC_GRPC_JAVA_ARTIFACTS, # IO_GRPC_GRPC_JAVA_ARTIFACTS is the external dependencies
        repositories = [
            "https://jcenter.bintray.com/",
            "https://repo1.maven.org/maven2",
        ],
        generate_compat_repositories = True,
        override_targets = IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS,
    )
