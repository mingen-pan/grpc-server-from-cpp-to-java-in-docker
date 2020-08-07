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
    name = "rpc_service_cc_grpc",
    srcs = [":rpc_service_proto"],
    grpc_only = True,
    deps = [":rpc_service_cc_proto"],
)

java_proto_library(
    name = "rpc_service_java_proto",
    deps = [":rpc_service_proto"],
)

java_grpc_library(
    name = "rpc_service_java_grpc",
    srcs = [":rpc_service_proto"],
    deps = [":rpc_service_java_proto"],
)