package custom.tibame201020.grpc_client.zmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.zeromq.dsl.ZeroMq;
import org.springframework.messaging.handler.annotation.Header;
import org.zeromq.SocketType;
import org.zeromq.ZContext;

import java.util.Optional;

@Configuration
public class ZeroMqConfig {

    @Bean
    public ZContext zContext() {
        return new ZContext();
    }

    @Bean
    public IntegrationFlow zeroMQPublishIntegrationFlow(ZContext zContext) {
        return IntegrationFlow.from("zeroMqPublishChannel")
                .handle(ZeroMq.outboundChannelAdapter(zContext, 12668, SocketType.PUB)
                        .topicFunction(message -> {
                            Object topic = message.getHeaders().getOrDefault("topic", "default-topic");
                            return Optional.ofNullable(topic).map(Object::toString).orElseGet(() -> "default-topic");
                        }))
                .get();
    }

    @MessagingGateway(defaultRequestChannel = "zeroMqPublishChannel")
    public interface ZeroMqPublishChannel {
        void pub(String payload, @Header("topic") String topic);

        void pub(String payload);
    }


}
