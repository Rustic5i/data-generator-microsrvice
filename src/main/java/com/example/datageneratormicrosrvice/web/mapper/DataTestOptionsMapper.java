package com.example.datageneratormicrosrvice.web.mapper;

import com.example.datageneratormicrosrvice.model.test.DataTestOptions;
import com.example.datageneratormicrosrvice.web.dto.DataTestOptionsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper
        extends Mappable<DataTestOptions, DataTestOptionsDto> {
}