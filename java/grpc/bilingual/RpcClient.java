package grpc.bilingual;

import io.grpc.Channel;

import java.util.Properties;

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
    int port = RpcProperties.get().getPort(/*default=*/50051);
    return RpcServiceProvider.loadChannel(port);
  }

  public static String hello(String name) {
    RpcServices.HelloRequest request = RpcServices.HelloRequest.newBuilder().setName(name).build();
    RpcServices.HelloResponse response = getStub().hello(request);
    return response.getGreeting();
  }
}
