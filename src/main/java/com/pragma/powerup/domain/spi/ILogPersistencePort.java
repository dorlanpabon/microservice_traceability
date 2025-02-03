package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Log;

import java.util.List;

public interface ILogPersistencePort {

    void saveLog(Log log);

    List<Log> getAllLog();

    List<Log> getLogsByOrderId(Long orderId);

}
