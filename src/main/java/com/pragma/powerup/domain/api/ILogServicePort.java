package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Log;

import java.util.List;

public interface ILogServicePort {

    void saveLog(Log log);

    List<Log> getAllLog();

    List<Log> getLogsByOrderId(Long orderId);

    Long getLogsTimeByOrderId(Long orderId);

    Double getAverageTimeByOrders(List<Long> orderIds);

    List<Log> getLogsTimeByOrders(List<Long> orderIds);
}
