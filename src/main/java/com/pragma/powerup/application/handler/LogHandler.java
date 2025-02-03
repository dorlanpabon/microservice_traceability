package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.LogRequest;
import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.application.dto.LogTimeResponse;
import com.pragma.powerup.application.mapper.LogRequestMapper;
import com.pragma.powerup.application.mapper.LogResponseMapper;
import com.pragma.powerup.domain.api.ILogServicePort;
import com.pragma.powerup.domain.model.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//@Transactional
public class LogHandler implements ILogHandler {

    private final LogRequestMapper logRequestMapper;
    private final LogResponseMapper logResponseMapper;
    private final ILogServicePort logServicePort;

    @Override
    public void saveLogInLog(LogRequest logRequest) {
        Log log = logRequestMapper.toLog(logRequest);
        logServicePort.saveLog(log);
    }

    @Override
    public List<LogResponse> getLogFromLog() {
        return logServicePort.getAllLog().stream()
                .map(logResponseMapper::toLogResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<LogResponse> getLogsByOrderId(Long orderId) {
        return logServicePort.getLogsByOrderId(orderId).stream()
                .map(logResponseMapper::toLogResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LogTimeResponse getLogsTimeByOrderId(Long orderId) {
        Long time = logServicePort.getLogsTimeByOrderId(orderId);
        return new LogTimeResponse(orderId, time);
    }

    @Override
    public Double getAverageTimeByOrders(List<Long> orderIds) {
        return logServicePort.getAverageTimeByOrders(orderIds);
    }

    @Override
    public List<LogTimeResponse> getLogsTimeByOrders(List<Long> orderIds) {
        return logServicePort.getLogsTimeByOrders(orderIds).stream()
                .map(logResponseMapper::toLogTimeResponse)
                .collect(Collectors.toList());
    }

}
