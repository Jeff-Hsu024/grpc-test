package custom.tibame201020.grpc_server.zmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.zeromq.dsl.ZeroMq;
import org.springframework.messaging.Message;
import org.zeromq.SocketType;
import org.zeromq.ZContext;

@Configuration
public class ZeroMqConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public ZContext zContext() {
        return new ZContext();
    }

    @Bean
    public IntegrationFlow zeroMQPublishIntegrationFlow(ZContext zContext) {
        return IntegrationFlow.from(
                        ZeroMq.inboundChannelAdapter(zContext, SocketType.SUB)
                                .connectUrl("tcp://localhost:12668")
                                .topics("topicOne", "topicXxxx")
                                .receiveRaw(true)
                )
                .handle(this::messageConsumer)
                .get();
    }

    void messageConsumer(Message<?> message) {
        logger.info("receive message payload: {}", message.getPayload());
        logger.info("receive message headers: {}", message.getHeaders());
    }

}
