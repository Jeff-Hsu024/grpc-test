package custom.tibame201020.grpc_client;

import custom.tibame201020.grpc_client.proto.GreeterGrpc;
import custom.tibame201020.grpc_client.proto.HelloRequest;
import custom.tibame201020.grpc_client.proto.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class GreeterClient {
    private final GreeterGrpc.GreeterBlockingStub blockingStub;
    public GreeterClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        this.blockingStub = GreeterGrpc.newBlockingStub(channel);
    }
    public String sayHello(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloResponse response = blockingStub.sayHello(request);
        return response.getMessage();
    }
}
