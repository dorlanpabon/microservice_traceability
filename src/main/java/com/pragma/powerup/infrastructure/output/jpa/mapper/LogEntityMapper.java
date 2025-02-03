package com.pragma.powerup.infrastructure.output.jpa.mapper;

import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.domain.model.Log;
import com.pragma.powerup.infrastructure.output.jpa.entity.LogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LogEntityMapper {

    LogEntity toEntity(Log log);

    Log toLog(LogEntity logEntity);

    List<Log> toLogList(List<LogEntity> logEntityList);

}
