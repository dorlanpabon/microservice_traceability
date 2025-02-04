package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.enums.OrderStatusEnum;
import com.pragma.powerup.domain.model.Log;
import com.pragma.powerup.domain.spi.ILogPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

class LogUseCaseTest {
    @Mock
    ILogPersistencePort logPersistencePort;
    @InjectMocks
    LogUseCase logUseCase;
    @Mock
    Log log;
    @Mock
    List<Log> logs;
    @Mock
    List<Long> orderIds;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        log = new Log();
        log.setType(OrderStatusEnum.PENDING.toString());
        log.setTimestamp(LocalDateTime.now());
        log.setClientId(1L);
        log.setOrderId(1L);
        log.setRestaurantId(1L);

        logs = List.of(log);

        orderIds = List.of(Long.valueOf(1));
    }

    @Test
    void testSaveLog() {
        logUseCase.saveLog(log);
        verify(logPersistencePort).saveLog(any(Log.class));
    }

    @Test
    void testGetAllLog() {
        when(logPersistencePort.getAllLog()).thenReturn(List.of(log));

        List<Log> result = logUseCase.getAllLog();
        Assertions.assertEquals(List.of(log), result);
    }

    @Test
    void testGetLogsByOrderId() {
        when(logPersistencePort.getLogsByOrderId(anyLong())).thenReturn(List.of(log));

        List<Log> result = logUseCase.getLogsByOrderId(Long.valueOf(1));
        Assertions.assertEquals(List.of(log), result);
    }

    @Test
    void testGetLogsTimeByOrderId() {
        when(logPersistencePort.getLogsByOrderId(anyLong())).thenReturn(logs);

        Long result = logUseCase.getLogsTimeByOrderId(Long.valueOf(1));
        verify(logPersistencePort).getLogsByOrderId(anyLong());
    }

    @Test
    void testGetAverageTimeByOrders() {
        when(logPersistencePort.getLogsByOrderId(anyLong())).thenReturn(List.of(log));

        Double result = logUseCase.getAverageTimeByOrders(List.of(Long.valueOf(1)));
        Assertions.assertEquals(Double.valueOf(0), result);
    }

    @Test
    void testGetLogsTimeByOrders() {
        when(logPersistencePort.getLogsByOrderId(anyLong())).thenReturn(logs);

        List<Log> result = logUseCase.getLogsTimeByOrders(List.of(Long.valueOf(1)));
        verify(logPersistencePort).getLogsByOrderId(anyLong());
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme