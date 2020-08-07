#include <iostream>

#include <grpcpp/grpcpp.h>
#include <grpcpp/health_check_service_interface.h>
#include <grpcpp/ext/proto_server_reflection_plugin.h>

#include "cpp/rpc_service/service.h"


using grpc_bilingual::service::CppServerGrpcImpl;

void RunServer(const std::string &server_address) {
    CppServerGrpcImpl service;

    grpc::EnableDefaultHealthCheckService(true);
    grpc::reflection::InitProtoReflectionServerBuilderPlugin();
    grpc::ServerBuilder builder;
    // Listen on the given address without any authentication mechanism.
    builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
    // Register "service" as the instance through which we'll communicate with
    // clients. In this case it corresponds to an *synchronous* service.
    builder.RegisterService(&service);
    // Finally assemble the rpc_service.
    std::unique_ptr<grpc::Server> server(builder.BuildAndStart());
    std::cout << "Server listening on " << server_address << std::endl;

    // Wait for the rpc_service to shutdown. Note that some other thread must be
    // responsible for shutting down the rpc_service for this call to ever return.
    server->Wait();
}


int main() {
    RunServer("0.0.0.0:50051");
    return 0;
}