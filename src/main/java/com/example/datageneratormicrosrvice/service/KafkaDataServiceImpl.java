package com.example.datageneratormicrosrvice.service;

import com.example.datageneratormicrosrvice.model.Data;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final KafkaSender<String, Object> sender;

    public void send(Data data) {
        final String topic = getTopic(data);
        final int numberPartition = 0;
        final long timestamp = System.currentTimeMillis();
        final String key = String.valueOf(data.hashCode());

        Publisher<SenderRecord<String, Object, Object>> just = Mono.just(
                SenderRecord.create(topic, numberPartition, timestamp,
                        key, data, null)
        );
        sender.send(just)
                .subscribe();
    }

    private static String getTopic(Data data) {
        return switch (data.getMeasurementType()) {
            case TEMPERATURE -> "data-temperature";
            case VOLTAGE -> "data-voltage";
            case POWER -> "data-power";
        };
    }
}
