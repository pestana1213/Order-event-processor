package com.example.order_events_processor.KafkaConfiguration;

import com.example.order_events_processor.DTO.OrderDto;
import com.example.order_events_processor.Exceptions.OrderException;
import com.example.order_events_processor.MessagesMappers.MessageMapper;
import com.example.order_events_processor.MessagesMappers.MessageToOrder;
import com.example.order_events_processor.Services.OrderService;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.apache.kafka.common.serialization.Serdes;

import java.util.Properties;
import java.util.stream.Stream;

@EnableKafka
@EnableKafkaStreams
@Configuration
public class KafkaStreamsConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.streams.application-id}")
    private String applicationId;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageToOrder messageToOrder;

    // Kafka Streams properties
    @Bean
    public Properties streamsConfig() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return properties;
    }

    @Bean
    public KStream<String, String> orderStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder.stream("create-order", Consumed.with(Serdes.String(), Serdes.String()));

        KStream<String, String> failedStream = stream
            .mapValues(value -> {
                try {
                    OrderDto orderDto = messageToOrder.map(value);
                    orderService.createOrder(orderDto);
                    return null;
                } catch (OrderException e) {
                    return value;
                }
            })
            .filter((key, value) -> value != null);

        failedStream.to("failed-orders", Produced.with(Serdes.String(), Serdes.String()));

        return stream;
    }
}
