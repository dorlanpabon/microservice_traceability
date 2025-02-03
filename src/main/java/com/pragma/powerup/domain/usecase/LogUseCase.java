package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ILogServicePort;
import com.pragma.powerup.domain.enums.OrderStatusEnum;
import com.pragma.powerup.domain.exception.DomainException;
import com.pragma.powerup.domain.model.Log;
import com.pragma.powerup.domain.spi.ILogPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogUseCase implements ILogServicePort {

    private final ILogPersistencePort logPersistencePort;

    public LogUseCase(ILogPersistencePort logPersistencePort) {
        this.logPersistencePort = logPersistencePort;
    }

    @Override
    public void saveLog(Log log) {
        logPersistencePort.saveLog(log);
    }

    @Override
    public List<Log> getAllLog() {
        return logPersistencePort.getAllLog();
    }

    @Override
    public List<Log> getLogsByOrderId(Long orderId) {
        return logPersistencePort.getLogsByOrderId(orderId);
    }

    @Override
    public Long getLogsTimeByOrderId(Long orderId) {
        List<Log> logs = logPersistencePort.getLogsByOrderId(orderId);
        LocalDateTime timeInInit = null;
        LocalDateTime timeInEnd = null;
        for (Log log : logs) {
            if (log.getType().equals(OrderStatusEnum.PENDING.toString())) {
                timeInInit = log.getTimestamp();
            }
            if (log.getType().equals(OrderStatusEnum.DELIVERED.toString())) {
                timeInEnd = log.getTimestamp();
            }
        }

        if (timeInInit == null || timeInEnd == null) {
           return 0L;
        }

        return Duration.between(timeInInit, timeInEnd).getSeconds();
    }

    @Override
    public Double getAverageTimeByOrders(List<Long> orderIds) {
        Long totalSeconds = 0L;
        for (Long orderId : orderIds) {
            totalSeconds += getLogsTimeByOrderId(orderId);
        }
        return totalSeconds.doubleValue() / orderIds.size();
    }

    @Override
    public List<Log> getLogsTimeByOrders(List<Long> orderIds) {
        List<Log> logsWithTime = new ArrayList<>();

        for (Long orderId : orderIds) {
            Long timeInSeconds = getLogsTimeByOrderId(orderId);
            Log log = new Log();
            log.setOrderId(orderId);
            log.setTime(timeInSeconds);
            logsWithTime.add(log);
        }

        return logsWithTime;
    }

}
