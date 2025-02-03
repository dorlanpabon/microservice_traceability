package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.application.dto.LogTimeResponse;
import com.pragma.powerup.domain.model.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LogResponseMapper {
    @Mapping(target = "logId", source = "id")
    @Mapping(target = "logType", source = "type")
    @Mapping(target = "logClientId", source = "clientId")
    @Mapping(target = "logOrderId", source = "orderId")
    @Mapping(target = "logRestaurantId", source = "restaurantId")
    @Mapping(target = "logTimestamp", source = "timestamp")
    LogResponse toLogResponse(Log entity);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "time", source = "time")
    LogTimeResponse toLogTimeResponse(Log log);
}
