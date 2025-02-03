package com.pragma.powerup.domain.spi;

import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.domain.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ILogPersistencePort {

    void saveLog(Log log);

    List<Log> getAllLog();

    Log getLogById(String logId);

    void updateLog(Log log);

    void deleteLog(String logId);

    Page<Log> getLogs(int page, int size, boolean ascending);

    Page<Log> getLogs(int pageNumber, int pageSize, String sortDirection);

    Page<Log> getLogs(PageRequest pageRequest);

    Page<Log> findAll(Pageable pageable);

    List<Log> getLogsByOrderId(Long orderId);

    List<Log> getLogsByOrderIds(List<Long> orderIds);
}
