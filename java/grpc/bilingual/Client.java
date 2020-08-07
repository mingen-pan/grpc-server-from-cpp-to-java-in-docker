package grpc.bilingual;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

public class Client {

  private static RpcServiceGrpc.RpcServiceBlockingStub stub;

  private Client() {}

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

}
