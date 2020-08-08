package grpc.bilingual;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

import java.util.Collections;
import java.util.List;

public class RpcServiceDockerProvider implements RpcServiceProvider {

    private final static Integer SERVER_PORT = 50051;
    private final static String ADDRESS_PREFIX = "localhost:";
    private final static String IMAGE = RpcProperties.get().getImage(/*default=*/"");
    private final static String RUNNING = "running";
    // waiting time for starting server. If the server container con not start in this period, it will be considered shutdown.
    // Unit is millisecond.
    private final static long START_SERVER_TIMEOUT = 5000;

    private final DockerClient client;

    public RpcServiceDockerProvider() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .build();

        client = DockerClientImpl.getInstance(config, httpClient);
    }

    @Override
    public void startServer(final Integer port) {
        String binding = String.format("%d:%d", port, SERVER_PORT);
        CreateContainerResponse response =
                client
                        .createContainerCmd(IMAGE)
                        .withExposedPorts(ExposedPort.parse("" + SERVER_PORT))
                        .withHostConfig(
                                HostConfig.newHostConfig().withPortBindings(PortBinding.parse(binding)))
                        .exec();

        String containerId = response.getId();
        client.startContainerCmd(containerId).exec();

        if (!waitServerToStart(containerId)) {
            throw new RpcServiceException("Unable to start ZetaSql Helper Service Docker container");
        }
    }

    @Override
    public boolean isServerOn(final Integer port) {
        List<Container> containers = client.listContainersCmd()
                .withAncestorFilter(Collections.singletonList(IMAGE))
                .exec();

        for (Container container : containers) {
            ContainerPort[] containerPorts = container.getPorts();
            for (ContainerPort containerPort : containerPorts) {
                if (SERVER_PORT.equals(containerPort.getPrivatePort()) && port.equals(containerPort.getPublicPort())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Channel connect(final Integer port) {
        try {
            // if the server is not active, try to start it.
            if (!isServerOn(port)) {
                startServer(port);
            }
        } catch (RuntimeException exception) {
            throw new RpcServiceException("unable to create a channel to RPC Server through Docker", exception);
        }

        return ManagedChannelBuilder.forTarget(ADDRESS_PREFIX + port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext()
                .build();
    }

    private boolean waitServerToStart(String ContainerId) {
        long now = System.currentTimeMillis();
        long endTime = now + RpcServiceDockerProvider.START_SERVER_TIMEOUT;

        for (; now < endTime; now = System.currentTimeMillis()) {
            List<Container> containers = client.listContainersCmd()
                    .withIdFilter(Collections.singletonList(ContainerId))
                    .exec();
            if (containers.size() == 0) {
                continue;
            }
            if (containers.get(0).getState().equals(RUNNING)) {
                return true;
            }
        }

        return false;
    }

}
