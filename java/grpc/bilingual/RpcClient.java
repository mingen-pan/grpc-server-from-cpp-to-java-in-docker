package grpc.bilingual;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

public class RpcClient {

  private static RpcServiceGrpc.RpcServiceBlockingStub stub;

  private RpcClient() {}

  public static RpcServiceGrpc.RpcServiceBlockingStub getStub() {
    if (stub == null) {
      Channel channel = getChannel();
      stub = RpcServiceGrpc.newBlockingStub(channel);
    }
    return stub;
  }

  private static Channel getChannel() {
    return ManagedChannelBuilder.forTarget("localhost:50051")
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext()
            .build();
  }

  public static String hello(String name) {
    getStub();
    RpcServices.HelloRequest request = RpcServices.HelloRequest.newBuilder().setName(name).build();
    RpcServices.HelloResponse response = stub.hello(request);
    return response.getGreeting();
  }
}
