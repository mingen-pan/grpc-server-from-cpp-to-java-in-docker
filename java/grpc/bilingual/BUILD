package(
    default_visibility = ["//visibility:public"],
)


CLIENT_SRCS = [
    "RpcClient.java",
]

java_library(
    name = "client",
    srcs = CLIENT_SRCS,
    runtime_deps = [
        "@maven//:io_grpc_grpc_netty_shaded",
    ],
    deps = [
        "//proto:rpc_service_java_grpc",
        "//proto:rpc_service_java_proto",
        "@maven//:io_grpc_grpc_api",
        "@maven//:io_grpc_grpc_protobuf",
        "@maven//:io_grpc_grpc_stub",
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
