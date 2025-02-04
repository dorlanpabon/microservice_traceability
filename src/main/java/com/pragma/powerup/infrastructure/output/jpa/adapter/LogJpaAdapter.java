package com.pragma.powerup.infrastructure.output.jpa.adapter;

import com.pragma.powerup.domain.model.Log;
import com.pragma.powerup.domain.spi.ILogPersistencePort;
import com.pragma.powerup.infrastructure.output.jpa.entity.LogEntity;
import com.pragma.powerup.infrastructure.output.jpa.mapper.LogEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.ILogRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LogJpaAdapter implements ILogPersistencePort {

    private final ILogRepository logRepository;

    private final LogEntityMapper logEntityMapper;

    @Override
    public void saveLog(Log log) {
        logRepository.save(logEntityMapper.toEntity(log));
    }

    @Override
    public List<Log> getAllLog() {
        List<LogEntity> entityList = logRepository.findAll();
        return logEntityMapper.toLogList(entityList);
    }

    @Override
    public List<Log> getLogsByOrderId(Long orderId) {
        List<LogEntity> entityList = logRepository.findByOrderId(orderId);
        return logEntityMapper.toLogList(entityList);
    }

}
