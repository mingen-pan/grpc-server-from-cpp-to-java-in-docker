package(
    default_visibility = ["//visibility:public"],
)

cc_binary(
    name = "run-server",
    srcs = ["run_server.cc"],
    deps = [
        "//cpp/rpc_service:cpp_service_impl",
        "@com_github_grpc_grpc//:grpc++",
        "@com_github_grpc_grpc//:grpc++_reflection",
    ],
)