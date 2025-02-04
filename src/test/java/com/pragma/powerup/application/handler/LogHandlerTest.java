package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.LogRequest;
import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.application.dto.LogTimeResponse;
import com.pragma.powerup.application.mapper.LogRequestMapper;
import com.pragma.powerup.application.mapper.LogResponseMapper;
import com.pragma.powerup.domain.api.ILogServicePort;
import com.pragma.powerup.domain.model.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class LogHandlerTest {
    @Mock
    LogRequestMapper logRequestMapper;
    @Mock
    LogResponseMapper logResponseMapper;
    @Mock
    ILogServicePort logServicePort;
    @InjectMocks
    LogHandler logHandler;
    @Mock
    Log log;
    @Mock
    LogRequest logRequest;
    @Mock
    LogResponse logResponse;
    @Mock
    LogTimeResponse logTimeResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        log = new Log();
        log.setId("ASDF1234");
        log.setType("INFO");
        log.setOrderId(1L);
        log.setClientId(1L);
        log.setRestaurantId(1L);

        logRequest = new LogRequest();
        logRequest.setId("ASDF1234");
        logRequest.setType("INFO");
        logRequest.setOrderId(1L);
        logRequest.setClientId(1L);
        logRequest.setRestaurantId(1L);

        logResponse = new LogResponse();
        logResponse.setLogId("ASDF1234");
        logResponse.setLogType("INFO");
        logResponse.setLogOrderId(1L);
        logResponse.setLogClientId(1L);
        logResponse.setLogRestaurantId(1L);

        logTimeResponse = new LogTimeResponse();
        logTimeResponse.setTime(1L);
        logTimeResponse.setOrderId(1L);
    }

    @Test
    void testSaveLogInLog() {
        when(logRequestMapper.toLog(any(LogRequest.class))).thenReturn(log);

        logHandler.saveLogInLog(logRequest);
        verify(logServicePort).saveLog(any(Log.class));
    }

    @Test
    void testGetLogFromLog() {
        when(logServicePort.getAllLog()).thenReturn(List.of(log));
        when(logResponseMapper.toLogResponse(any(Log.class))).thenReturn(logResponse);

        List<LogResponse> result = logHandler.getLogFromLog();
        Assertions.assertEquals(List.of(logResponse), result);
    }

    @Test
    void testGetLogsByOrderId() {
        when(logServicePort.getLogsByOrderId(anyLong())).thenReturn(List.of(log));
        when(logResponseMapper.toLogResponse(any(Log.class))).thenReturn(logResponse);

        List<LogResponse> result = logHandler.getLogsByOrderId(Long.valueOf(1));
        Assertions.assertEquals(List.of(logResponse), result);
    }

    @Test
    void testGetLogsTimeByOrderId() {
        when(logServicePort.getLogsTimeByOrderId(anyLong())).thenReturn(Long.valueOf(1));
        when(logResponseMapper.toLogTimeResponse(anyLong(), anyLong())).thenReturn(logTimeResponse);

        LogTimeResponse result = logHandler.getLogsTimeByOrderId(Long.valueOf(1));
        Assertions.assertEquals(logTimeResponse, result);
    }

    @Test
    void testGetAverageTimeByOrders() {
        when(logServicePort.getAverageTimeByOrders(any(List.class))).thenReturn(Double.valueOf(0));

        Double result = logHandler.getAverageTimeByOrders(List.of(Long.valueOf(1)));
        Assertions.assertEquals(Double.valueOf(0), result);
    }

    @Test
    void testGetLogsTimeByOrders() {
        when(logServicePort.getLogsTimeByOrders(any(List.class))).thenReturn(List.of(log));
        when(logResponseMapper.toLogTimeResponse(any(Log.class))).thenReturn(logTimeResponse);

        List<LogTimeResponse> result = logHandler.getLogsTimeByOrders(List.of(Long.valueOf(1)));
        Assertions.assertEquals(List.of(logTimeResponse), result);
    }
}

