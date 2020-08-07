package grpc.bilingual;

public class Main {

    public static void main(String[] args) {
        RpcServiceGrpc.RpcServiceBlockingStub stub = Client.getStub();
        RpcServices.HelloRequest request = RpcServices.HelloRequest.newBuilder().setName("Test").build();
        RpcServices.HelloResponse response = stub.hello(request);
        System.out.println(response.getGreeting());
    }

}


