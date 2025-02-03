package com.pragma.powerup.domain.api;

import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.domain.model.Log;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

public interface ILogServicePort {

    void saveLog(Log log);

    List<Log> getAllLog();

    Log getLogById(String logId);

    void updateLog(Log log);

    void deleteLog(String logId);

    Page<Log> getLogs(int page, int size, boolean ascending);

    Page<Log> getLogs(int pageNumber, int pageSize, String sortDirection);

    List<Log> getLogsByOrderId(Long orderId);

    Long getLogsTimeByOrderId(Long orderId);

    Double getAverageTimeByOrders(List<Long> orderIds);

    List<Log> getLogsTimeByOrders(List<Long> orderIds);
}
