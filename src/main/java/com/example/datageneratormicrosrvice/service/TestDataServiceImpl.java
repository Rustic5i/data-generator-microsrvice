package com.example.datageneratormicrosrvice.service;

import com.example.datageneratormicrosrvice.model.Data;
import com.example.datageneratormicrosrvice.model.test.DataTestOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TestDataServiceImpl implements TestDataService {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final KafkaDataService kafkaDataService;

    @Override
    public void sendMessages(DataTestOptions testOptions) {
        if (testOptions.getMeasurementTypes().length > 0) {
            runScheduled(testOptions);
        }
    }

    private void runScheduled(DataTestOptions testOptions) {
        int period = testOptions.getDelayInSeconds();
        executorService.scheduleAtFixedRate(
                () -> executorTask(testOptions),
                0,
                period,
                TimeUnit.SECONDS
        );
    }

    private void executorTask(DataTestOptions testOptions) {
        Data data = new Data();
        data.setSensorId((long) getRandomNumber(1, 10));
        data.setMeasurement(getRandomNumber(15, 20));
        data.setMeasurementType(getRandomMeasurement(testOptions.getMeasurementTypes()));
        data.setTimestamp(LocalDateTime.now());
        kafkaDataService.send(data);
    }

    private double getRandomNumber(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    private Data.MeasurementType getRandomMeasurement(Data.MeasurementType[] measurementTypes) {
        int randomTypeId = (int) (Math.random() * measurementTypes.length);
        return measurementTypes[randomTypeId];
    }

}
