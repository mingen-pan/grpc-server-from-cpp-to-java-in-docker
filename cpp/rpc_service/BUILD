load("@com_github_grpc_grpc//bazel:cc_grpc_library.bzl", "cc_grpc_library")
load("@io_grpc_grpc_java//:java_grpc_library.bzl", "java_grpc_library")

package(
    default_visibility = ["//visibility:public"],
)

proto_library(
    name = "rpc_service_proto",
    srcs = ["rpc_service.proto"],
    deps = [],
)

cc_proto_library(
    name = "rpc_service_cc_proto",
    deps = [":rpc_service_proto"],
)

cc_grpc_library(
    name = "rpc_serice_cc",
    srcs = [":rpc_service_proto"],
    grpc_only = True,
    deps = [":rpc_service_cc_proto"],
)

cc_library(
    name = "cpp_service_impl",
    srcs = ["service.cc"],
    hdrs = ["service.h"],
    copts = ["-Wno-sign-compare"],
    deps = [
        ":rpc_serice_cc",
        ":rpc_service_cc_proto",
         "@com_github_grpc_grpc//:grpc++",
        # dep regarding implementation

    ],
)