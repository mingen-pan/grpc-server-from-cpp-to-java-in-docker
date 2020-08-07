package(
    default_visibility = ["//visibility:public"],
)


cc_library(
    name = "cpp_service_impl",
    srcs = ["service.cc"],
    hdrs = ["service.h"],
    copts = ["-Wno-sign-compare"],
    deps = [
        "//proto:rpc_service_cc_grpc",
        "//proto:rpc_service_cc_proto",
        "@com_github_grpc_grpc//:grpc++",
        # dep regarding implementation
    ],
)
