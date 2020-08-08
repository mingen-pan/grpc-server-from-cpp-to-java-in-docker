package grpc.bilingual;

public class RpcServiceException extends RuntimeException {

    public RpcServiceException(String message, RuntimeException exception) {
        super(message, exception);
    }

    public RpcServiceException(String message) {
        super(message);
    }
}
