CLIENT_SRCS = [
    "Client.java",
]

java_library(
    name = "client",
    srcs = CLIENT_SRCS,
    runtime_deps = [
        "@maven//:io_grpc_grpc_netty_shaded",
    ],
    deps = [
        #        ":service_provider",
        "//cpp/rpc_service:rpc_service_java",
        "//cpp/rpc_service:rpc_service_java_proto",
        "@com_google_api_grpc_proto_google_common_protos//jar",
        "@com_google_protobuf//:protobuf_java",
        "@com_google_protobuf//:protobuf_java_util",
        #        "@io_grpc_grpc_core//jar",
        "@maven//:io_grpc_grpc_api",
        "@maven//:io_grpc_grpc_protobuf",
        "@maven//:io_grpc_grpc_stub",
        #        "@maven//:org_projectlombok_lombok",
    ],
)

java_binary(
    name = "run_client",
    srcs = ["Main.java"],
    main_class = "grpc.bilingual.Main",
    runtime_deps = [
        "@maven//:io_grpc_grpc_netty_shaded",
    ],
    deps = [
        ":client",
        "//cpp/rpc_service:rpc_service_java",
        "//cpp/rpc_service:rpc_service_java_proto",
    ],
)
