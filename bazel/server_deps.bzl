load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")


def download_rpc_server_deps():

    if not native.existing_rule("com_github_grpc_grpc"):
        http_archive(
            name = "com_github_grpc_grpc",
            urls = [
                "https://github.com/grpc/grpc/archive/v1.30.0.tar.gz",
            ],
            strip_prefix = "grpc-1.30.0",
        )

    # gRPC Java
    if not native.existing_rule("io_grpc_grpc_java"):
        http_archive(
            name = "io_grpc_grpc_java",
            # Release 1.22.1
            url = "https://github.com/grpc/grpc-java/archive/v1.30.0.tar.gz",
            strip_prefix = "grpc-java-1.30.0",
        )
