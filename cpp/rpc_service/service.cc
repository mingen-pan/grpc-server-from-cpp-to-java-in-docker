#include "cpp/rpc_service/service.h"
#include <string>

namespace grpc_bilingual::service {


    grpc::Status CppServerGrpcImpl::Hello(grpc::ServerContext *context, const HelloRequest *req, HelloResponse *resp) {
        std::string greet = "Hello, " + req->name();
        resp->set_greeting(greet);
        return grpc::Status::OK;
    }
}  // grpc_bilingual::rpc_service