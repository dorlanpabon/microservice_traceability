package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.LogRequest;
import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.application.dto.LogTimeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ILogHandler {

    Page<LogResponse> getLogs(int page, int size, String sortDirection);

    void saveLogInLog(LogRequest logRequest);

    List<LogResponse> getLogFromLog();

    LogResponse getLogFromLog(String logId);

    void updateLogInLog(LogRequest logRequest);

    void deleteLogFromLog(String logId);

    List<LogResponse> getLogsByOrderId(Long orderId);

    LogTimeResponse getLogsTimeByOrderId(Long orderId);

    Double getAverageTimeByOrders(List<Long> orderIds);

    List<LogTimeResponse> getLogsTimeByOrders(List<Long> orderIds);
}
