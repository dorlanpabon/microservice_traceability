package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.LogRequest;
import com.pragma.powerup.domain.model.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LogRequestMapperTest {

    private LogRequestMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(LogRequestMapper.class);
    }

    @Test
    void testToLog() {
        LogRequest request = new LogRequest();
        request.setId("log1");
        request.setType("INFO");
        request.setClientId(123L);
        request.setOrderId(456L);
        request.setRestaurantId(789L);
        request.setTimestamp(LocalDateTime.of(2023, 1, 1, 12, 30));

        Log log = mapper.toLog(request);

        assertNotNull(log, "El objeto Log no debe ser nulo");
        assertEquals(request.getId(), log.getId(), "El ID no coincide");
        assertEquals(request.getType(), log.getType(), "El tipo no coincide");
        assertEquals(request.getClientId(), log.getClientId(), "El clientId no coincide");
        assertEquals(request.getOrderId(), log.getOrderId(), "El orderId no coincide");
        assertEquals(request.getRestaurantId(), log.getRestaurantId(), "El restaurantId no coincide");
        assertEquals(request.getTimestamp(), log.getTimestamp(), "El timestamp no coincide");
    }
}
