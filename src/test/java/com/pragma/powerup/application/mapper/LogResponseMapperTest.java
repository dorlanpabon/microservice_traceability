package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.application.dto.LogTimeResponse;
import com.pragma.powerup.domain.model.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LogResponseMapperTest {

    private LogResponseMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(LogResponseMapper.class);
    }

    @Test
    void testToLogResponse() {
        Log log = new Log();
        log.setId("log123");
        log.setType("INFO");
        log.setClientId(100L);
        log.setOrderId(200L);
        log.setRestaurantId(300L);
        log.setTimestamp(LocalDateTime.of(2023, 1, 1, 12, 0));

        LogResponse response = mapper.toLogResponse(log);

        assertNotNull(response, "El objeto LogResponse no debe ser nulo");
        assertEquals("log123", response.getLogId());
        assertEquals("INFO", response.getLogType());
        assertEquals(100L, response.getLogClientId());
        assertEquals(200L, response.getLogOrderId());
        assertEquals(300L, response.getLogRestaurantId());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), response.getLogTimestamp());
    }

    @Test
    void testToLogTimeResponseFromLog() {
        Log log = new Log();
        log.setOrderId(200L);
        log.setTime(60L);

        LogTimeResponse response = mapper.toLogTimeResponse(log);

        assertNotNull(response, "El objeto LogTimeResponse no debe ser nulo");
        assertEquals(200L, response.getOrderId());
        assertEquals(60L, response.getTime());
    }

    @Test
    void testToLogTimeResponseFromParams() {
        Long orderId = 200L;
        Long time = 60L;

        LogTimeResponse response = mapper.toLogTimeResponse(time, orderId);

        assertNotNull(response, "El objeto LogTimeResponse no debe ser nulo");
        assertEquals(orderId, response.getOrderId());
        assertEquals(time, response.getTime());
    }
}
