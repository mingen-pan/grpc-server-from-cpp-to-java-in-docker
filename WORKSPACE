workspace(name = "grpc_server_from_cpp_to_java_in_docker")

#load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
#
#http_archive(
#    name = "com_github_grpc_grpc",
#    urls = [
#        "https://github.com/grpc/grpc/archive/v1.30.0.tar.gz",
#    ],
#    strip_prefix = "grpc-1.30.0",
#)
#
#load("@com_github_grpc_grpc//bazel:grpc_deps.bzl", "grpc_deps")
#
#grpc_deps()


load("//bazel:server_deps.bzl", "download_rpc_server_deps")
download_rpc_server_deps()

load("@com_github_grpc_grpc//bazel:grpc_deps.bzl", "grpc_deps")
grpc_deps()

load("@com_github_grpc_grpc//bazel:grpc_extra_deps.bzl", "grpc_extra_deps")
grpc_extra_deps()

load("@io_grpc_grpc_java//:repositories.bzl", "grpc_java_repositories")
grpc_java_repositories()



