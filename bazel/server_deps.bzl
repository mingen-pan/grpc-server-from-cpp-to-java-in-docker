load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")


def download_rpc_service_deps():

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

    # repo for jar_jar
    if not native.existing_rule("google_bazel_common"):
        http_archive(
            name = "google_bazel_common",
            strip_prefix = "bazel-common-e768dbfea5bac239734b3f59b2a1d7464c6dbd26",
            urls = ["https://github.com/google/bazel-common/archive/e768dbfea5bac239734b3f59b2a1d7464c6dbd26.zip"],
            sha256 = "17f66ba76073a290add024a4ce7f5f92883832b7da85ffd7677e1f5de9a36153",
        )

    # jvm_install
    if not native.existing_rule("rules_jvm_external"):
        http_archive(
            name = "rules_jvm_external",
            sha256 = "62133c125bf4109dfd9d2af64830208356ce4ef8b165a6ef15bbff7460b35c3a",
            strip_prefix = "rules_jvm_external-3.0",
            url = "https://github.com/bazelbuild/rules_jvm_external/archive/3.0.zip",
        )
