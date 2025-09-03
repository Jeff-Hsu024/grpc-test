package custom.tibame201020.grpc_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class, args);
    }

    @Bean
    RouterFunction<ServerResponse> routerFunction(GreeterClient greeterClient) {
        return RouterFunctions.route()
                .path("/test", builder ->
                        builder.GET(req ->
                                ServerResponse.ok().body(greeterClient.sayHello("from client"))
                        )
                )
                .build();
    }

}
