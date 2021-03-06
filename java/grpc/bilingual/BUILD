load("@google_bazel_common//tools/jarjar:jarjar.bzl", "jarjar_library")

package(
    default_visibility = ["//visibility:public"],
)

CLIENT_SRCS = [
    "RpcClient.java",
]

java_library(
    name = "client",
    srcs = CLIENT_SRCS,
    deps = [
        ":properties",
        ":service_provider",
        "//proto:rpc_service_java_grpc",
        "//proto:rpc_service_java_proto",
        "@maven//:io_grpc_grpc_api",
        "@maven//:io_grpc_grpc_protobuf",
        "@maven//:io_grpc_grpc_stub",
    ],
)

java_library(
    name = "service_provider",
    srcs = [
        "RpcServiceDockerProvider.java",
        "RpcServiceException.java",
        "RpcServiceProvider.java",
    ],
    runtime_deps = [
        "@maven//:io_grpc_grpc_netty_shaded",
    ],
    deps = [
        ":properties",
        "@maven//:com_github_docker_java_docker_java_api",
        "@maven//:com_github_docker_java_docker_java_core",
        "@maven//:com_github_docker_java_docker_java_transport",
        "@maven//:com_github_docker_java_docker_java_transport_httpclient5",
        "@maven//:io_grpc_grpc_api",
    ],
)

java_library(
    name = "properties",
    srcs = ["RpcProperties.java"],
    resource_strip_prefix = "java/resources/",
    resources = [
        "//java/resources:app-properties",
    ],
)

java_binary(
    name = "run_client",
    srcs = ["Main.java"],
    main_class = "grpc.bilingual.Main",
    deps = [
        ":client",
    ],
)

# build a light-weighted jar. It should be used along with dependencies.
# Here are the dependencies in Gradle:
# JAVA_GRPC_VERSION = 1.30.0
# implementation "io.grpc:grpc-netty-shaded:" + JAVA_GRPC_VERSION
# implementation "io.grpc:grpc-protobuf:" + JAVA_GRPC_VERSION
# implementation "io.grpc:grpc-stub:" + JAVA_GRPC_VERSION
jarjar_library(
    name = "lightweighted-client",
    jars = [
        ":client",
        ":service_provider",
        ":properties",
        "//proto:rpc_service_java_grpc",
        "//proto:rpc_service_java_proto",
    ],
    rules = [
        "zap com.google.protobuf.**",
    ],
)
