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


load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")
http_archive(
    name = "rules_jvm_external",
    sha256 = "62133c125bf4109dfd9d2af64830208356ce4ef8b165a6ef15bbff7460b35c3a",
    strip_prefix = "rules_jvm_external-3.0",
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/3.0.zip",
)

load("@rules_jvm_external//:defs.bzl", "maven_install")
load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS")
load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS")
JAVA_GRPC_VERSION = "1.30.0"

maven_install(
    artifacts = [
        "io.grpc:grpc-netty-shaded:" + JAVA_GRPC_VERSION,
        "io.grpc:grpc-protobuf:" + JAVA_GRPC_VERSION,
        "io.grpc:grpc-stub:" + JAVA_GRPC_VERSION,
        "io.grpc:grpc-api:" + JAVA_GRPC_VERSION,
#        "org.apache.tomcat:annotations-api:6.0.53",
        # docker java
        "com.github.docker-java:docker-java-core:3.2.5",
        "com.github.docker-java:docker-java-api:3.2.5",
        "com.github.docker-java:docker-java-transport-httpclient5:3.2.5",
        "com.github.docker-java:docker-java-transport:3.2.5",
    ] + IO_GRPC_GRPC_JAVA_ARTIFACTS, # IO_GRPC_GRPC_JAVA_ARTIFACTS is the external dependencies
    repositories = [
        "https://jcenter.bintray.com/",
        "https://repo1.maven.org/maven2",
    ],
    generate_compat_repositories = True,
    override_targets = IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS,
)

load("@maven//:compat.bzl", "compat_repositories")

compat_repositories()

load("@io_grpc_grpc_java//:repositories.bzl", "grpc_java_repositories")
grpc_java_repositories()


