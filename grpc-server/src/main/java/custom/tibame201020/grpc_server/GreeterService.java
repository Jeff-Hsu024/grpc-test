package custom.tibame201020.grpc_server;

import custom.tibame201020.grpc_server.proto.GreeterGrpc;
import custom.tibame201020.grpc_server.proto.HelloRequest;
import custom.tibame201020.grpc_server.proto.HelloResponse;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GreeterService extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        String message = "Hello, " + name + "!";
        HelloResponse response = HelloResponse.newBuilder().setMessage(message).build();
        // Send the response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
