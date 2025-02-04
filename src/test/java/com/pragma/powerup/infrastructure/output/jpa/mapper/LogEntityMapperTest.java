package com.pragma.powerup.infrastructure.output.jpa.mapper;

import com.pragma.powerup.domain.model.Log;
import com.pragma.powerup.infrastructure.output.jpa.entity.LogEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogEntityMapperTest {

    private LogEntityMapper logEntityMapper;

    @BeforeEach
    void setUp() {
        logEntityMapper = Mappers.getMapper(LogEntityMapper.class);
    }

    @Test
    void testToEntity() {
        Log log = new Log();
        log.setId("1");
        log.setType("INFO");
        log.setClientId(1L);
        log.setOrderId(2L);
        log.setRestaurantId(3L);
        log.setTimestamp(LocalDateTime.of(2023, 1, 1, 12, 0));
        log.setTime(100L);

        LogEntity entity = logEntityMapper.toEntity(log);

        assertNotNull(entity, "El objeto LogEntity no debe ser nulo");
        assertEquals("1", entity.getId());
        assertEquals("INFO", entity.getType());
        assertEquals(1L, entity.getClientId());
        assertEquals(2L, entity.getOrderId());
        assertEquals(3L, entity.getRestaurantId());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), entity.getTimestamp());
    }

    @Test
    void testToLog() {
        LogEntity entity = new LogEntity();
        entity.setId("1");
        entity.setType("INFO");
        entity.setClientId(1L);
        entity.setOrderId(2L);
        entity.setRestaurantId(3L);
        entity.setTimestamp(LocalDateTime.of(2023, 1, 1, 12, 0));

        Log log = logEntityMapper.toLog(entity);

        assertNotNull(log, "El objeto Log no debe ser nulo");
        assertEquals("1", log.getId());
        assertEquals("INFO", log.getType());
        assertEquals(1L, log.getClientId());
        assertEquals(2L, log.getOrderId());
        assertEquals(3L, log.getRestaurantId());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), log.getTimestamp());
    }

    @Test
    void testToLogList() {
        LogEntity entity1 = new LogEntity("1", "INFO", 1L, 2L, 3L, LocalDateTime.of(2023, 1, 1, 12, 0));
        LogEntity entity2 = new LogEntity("2", "ERROR", 4L, 5L, 6L, LocalDateTime.of(2023, 1, 2, 13, 0));
        List<LogEntity> entityList = Arrays.asList(entity1, entity2);

        List<Log> logs = logEntityMapper.toLogList(entityList);

        assertNotNull(logs, "La lista resultante no debe ser nula");
        assertEquals(2, logs.size(), "La lista debe tener 2 elementos");

        Log log1 = logs.get(0);
        assertEquals("1", log1.getId());
        assertEquals("INFO", log1.getType());
        assertEquals(1L, log1.getClientId());
        assertEquals(2L, log1.getOrderId());
        assertEquals(3L, log1.getRestaurantId());
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), log1.getTimestamp());

        Log log2 = logs.get(1);
        assertEquals("2", log2.getId());
        assertEquals("ERROR", log2.getType());
        assertEquals(4L, log2.getClientId());
        assertEquals(5L, log2.getOrderId());
        assertEquals(6L, log2.getRestaurantId());
        assertEquals(LocalDateTime.of(2023, 1, 2, 13, 0), log2.getTimestamp());
    }
}
