package com.pragma.powerup.infrastructure.output.jpa.adapter;

import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.domain.model.Log;
import com.pragma.powerup.domain.spi.ILogPersistencePort;
import com.pragma.powerup.infrastructure.output.jpa.entity.LogEntity;
import com.pragma.powerup.infrastructure.output.jpa.mapper.LogEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.ILogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class LogJpaAdapter implements ILogPersistencePort {

    private final ILogRepository logRepository;

    private final LogEntityMapper logEntityMapper;

    @Override
    public void saveLog(Log log) {
        //if (logRepository.findByName(log.getName()).isPresent()) {
        //    throw new IllegalArgumentException("Log already exists");
        //}
        logRepository.save(logEntityMapper.toEntity(log));
    }

    @Override
    public List<Log> getAllLog() {
        List<LogEntity> entityList = logRepository.findAll();
        if (entityList.isEmpty()) {
            throw new IllegalArgumentException("No Logs found");
        }
        return logEntityMapper.toLogList(entityList);
    }

    @Override
    public Log getLogById(String logId) {
        return logEntityMapper.toLog(logRepository.findById(logId)
                .orElseThrow(() -> new IllegalArgumentException("Log not found")));
    }

    @Override
    public void updateLog(Log log) {
        //if (logRepository.findByName(log.getName()).isPresent()) {
        //    throw new IllegalArgumentException("Log already exists");
        //}
        logRepository.save(logEntityMapper.toEntity(log));
    }

    @Override
    public void deleteLog(String logId) {
        logRepository.deleteById(logId);
    }

    @Override
    public Page<Log> getLogs(PageRequest pageRequest) {
        Page<LogEntity> entityPage = logRepository.findAll(pageRequest);
        if (entityPage.isEmpty()) {
            throw new IllegalArgumentException("No Logs found");
        }
        return entityPage.map(logEntityMapper::toLog);
    }

    @Override
    public Page<Log> getLogs(int page, int size, boolean ascending) {
        Pageable pageable = PageRequest.of(page, size, ascending ? Sort.by("id").ascending() : Sort.by("id").descending());
        return logRepository.findAll(pageable).map(logEntityMapper::toLog);
    }

    @Override
    public Page<Log> getLogs(int pageNumber, int pageSize, String sortDirection) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sortDirection.equals("asc") ? Sort.by("id").ascending() : Sort.by("id").descending());
        return logRepository.findAll(pageable).map(logEntityMapper::toLog);
    }

    @Override
    public Page<Log> findAll(Pageable pageable) {
        return logRepository.findAll(pageable).map(logEntityMapper::toLog);
    }

    @Override
    public List<Log> getLogsByOrderId(Long orderId) {
        List<LogEntity> entityList = logRepository.findByOrderId(orderId);
        return logEntityMapper.toLogList(entityList);
    }

    @Override
    public List<Log> getLogsByOrderIds(List<Long> orderIds) {
        List<LogEntity> entityList = logRepository.findByOrderIdIn(orderIds);
        return logEntityMapper.toLogList(entityList);
    }
}
