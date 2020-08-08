package grpc.bilingual;

import io.grpc.Channel;

import java.util.ServiceLoader;

public interface RpcServiceProvider {

    void startServer(Integer port);

    boolean isServerOn(Integer port);

    Channel connect(Integer port);


    static Channel loadChannel(Integer port) {
        RpcServiceProvider  provider= new RpcServiceDockerProvider();
        return provider.connect(port);
    }
}
