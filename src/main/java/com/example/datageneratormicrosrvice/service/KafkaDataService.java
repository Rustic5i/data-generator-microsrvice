package com.example.datageneratormicrosrvice.service;

import com.example.datageneratormicrosrvice.model.Data;

public interface KafkaDataService {

    void send(Data data);
}
