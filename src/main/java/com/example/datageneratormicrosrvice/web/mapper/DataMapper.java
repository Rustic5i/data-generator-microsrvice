package com.example.datageneratormicrosrvice.web.mapper;

import com.example.datageneratormicrosrvice.model.Data;
import com.example.datageneratormicrosrvice.web.dto.DataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto> {
}