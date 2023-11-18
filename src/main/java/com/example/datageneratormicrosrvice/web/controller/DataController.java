package com.example.datageneratormicrosrvice.web.controller;

import com.example.datageneratormicrosrvice.model.Data;
import com.example.datageneratormicrosrvice.model.test.DataTestOptions;
import com.example.datageneratormicrosrvice.service.KafkaDataService;
import com.example.datageneratormicrosrvice.service.TestDataService;
import com.example.datageneratormicrosrvice.web.dto.DataDto;
import com.example.datageneratormicrosrvice.web.dto.DataTestOptionsDto;
import com.example.datageneratormicrosrvice.web.mapper.DataMapper;
import com.example.datageneratormicrosrvice.web.mapper.DataTestOptionsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final KafkaDataService kafkaDataService;
    private final TestDataService testDataService;
    private final DataTestOptionsMapper dataTestOptionsMapper;
    private final DataMapper dataMapper;


    @PostMapping("/send")
    public void send(@RequestBody DataDto dataDto) {
        Data data = dataMapper.toEntity(dataDto);
        kafkaDataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody DataTestOptionsDto testOptionsDto) {
        DataTestOptions testOptions = dataTestOptionsMapper.toEntity(testOptionsDto);
        testDataService.sendMessages(testOptions);
    }

}