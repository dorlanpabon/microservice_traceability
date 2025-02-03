package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.LogRequest;
import com.pragma.powerup.domain.model.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LogRequestMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "timestamp", source = "timestamp")
    Log toLog(LogRequest logRequest);
}
