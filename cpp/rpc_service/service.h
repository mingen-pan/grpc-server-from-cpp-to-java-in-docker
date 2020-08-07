
#ifndef GRPC_SERVER_FROM_CPP_TO_JAVA_SERVICE_H
#define GRPC_SERVER_FROM_CPP_TO_JAVA_SERVICE_H

#include "proto/rpc_service.grpc.pb.h"
#include "proto/rpc_service.pb.h"

namespace grpc_bilingual::service {


    class CppServerGrpcImpl : public RpcService::Service {
    public:

        grpc::Status Hello(grpc::ServerContext *context, const HelloRequest *req,
                           HelloResponse *resp) override;

    };

}  // grpc_bilingual::rpc_service

#endif  // GRPC_SERVER_FROM_CPP_TO_JAVA_SERVICE_H
