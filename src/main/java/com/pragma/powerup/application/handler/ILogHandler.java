package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.LogRequest;
import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.application.dto.LogTimeResponse;

import java.util.List;

public interface ILogHandler {

    void saveLogInLog(LogRequest logRequest);

    List<LogResponse> getLogFromLog();

    List<LogResponse> getLogsByOrderId(Long orderId);

    LogTimeResponse getLogsTimeByOrderId(Long orderId);

    Double getAverageTimeByOrders(List<Long> orderIds);

    List<LogTimeResponse> getLogsTimeByOrders(List<Long> orderIds);
}
