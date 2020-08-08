workspace(name = "grpc_server_from_cpp_to_java_in_docker")


# download gPRC
load("//bazel:server_deps.bzl", "download_rpc_service_deps")
download_rpc_service_deps()

load("@com_github_grpc_grpc//bazel:grpc_deps.bzl", "grpc_deps")
grpc_deps()

load("@com_github_grpc_grpc//bazel:grpc_extra_deps.bzl", "grpc_extra_deps")
grpc_extra_deps()


# download maven
load("@//bazel:import_maven.bzl", "import_maven_repos")
import_maven_repos()

# compat repositories
load("@maven//:compat.bzl", "compat_repositories")
compat_repositories()

# Download gRPC Java
load("@io_grpc_grpc_java//:repositories.bzl", "grpc_java_repositories")
grpc_java_repositories()


# Download the repos for JarJar.
load("@google_bazel_common//:workspace_defs.bzl", "google_common_workspace_rules")
google_common_workspace_rules()

load("@bazel_skylib//:workspace.bzl", "bazel_skylib_workspace")
bazel_skylib_workspace()


