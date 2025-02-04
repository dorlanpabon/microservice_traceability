package com.pragma.powerup.infrastructure.output.jpa.adapter;

import com.pragma.powerup.domain.model.Log;
import com.pragma.powerup.infrastructure.output.jpa.entity.LogEntity;
import com.pragma.powerup.infrastructure.output.jpa.mapper.LogEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.ILogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogJpaAdapterTest {

    @Mock
    ILogRepository logRepository;

    @Mock
    LogEntityMapper logEntityMapper;

    @InjectMocks
    LogJpaAdapter logJpaAdapter;

    @Mock
    LogEntity logEntity;

    @Mock
    Log log;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        log = new Log();
        log.setId("id");
        log.setType("type");
        log.setClientId(1L);
        log.setOrderId(1L);
        log.setRestaurantId(1L);
        log.setTimestamp(LocalDateTime.of(2025, Month.FEBRUARY, 3, 22, 10, 32));

        logEntity = new LogEntity();
        logEntity.setId("id");
        logEntity.setType("type");
        logEntity.setClientId(1L);
        logEntity.setOrderId(1L);
        logEntity.setRestaurantId(1L);
        logEntity.setTimestamp(LocalDateTime.of(2025, Month.FEBRUARY, 3, 22, 10, 32));

        logJpaAdapter = new LogJpaAdapter(logRepository, logEntityMapper);
    }

    @Test
    void testSaveLog() {
        when(logEntityMapper.toEntity(any())).thenReturn(logEntity);
        when(logRepository.save(logEntity)).thenReturn(logEntity);

        logJpaAdapter.saveLog(log);

        verify(logEntityMapper, times(1)).toEntity(log);
        verify(logRepository, times(1)).save(logEntity);
    }


    @Test
    void testGetAllLog() {
        LogEntity dummyEntity = new LogEntity("id", "type", 1L, 1L, 1L,
                LocalDateTime.of(2025, Month.FEBRUARY, 3, 22, 10, 32));
        List<LogEntity> dummyEntityList = List.of(dummyEntity);
        when(logRepository.findAll()).thenReturn(dummyEntityList);

        List<Log> expectedLogs = List.of(new Log());
        when(logEntityMapper.toLogList(dummyEntityList)).thenReturn(expectedLogs);

        List<Log> result = logJpaAdapter.getAllLog();

        Assertions.assertNotNull(result, "El resultado no debe ser nulo");
        Assertions.assertEquals(expectedLogs.size(), result.size(), "El tamaño de la lista no coincide");
        Assertions.assertSame(expectedLogs, result, "La instancia devuelta no es la esperada");
    }

    @Test
    void testGetLogsByOrderId() {
        Long orderId = 1L;
        LogEntity dummyEntity = new LogEntity("id", "type", 1L, 1L, 1L,
                LocalDateTime.of(2025, Month.FEBRUARY, 3, 22, 10, 32));
        List<LogEntity> dummyEntityList = List.of(dummyEntity);
        when(logRepository.findByOrderId(orderId)).thenReturn(dummyEntityList);

        List<Log> expectedLogs = List.of(new Log());
        when(logEntityMapper.toLogList(dummyEntityList)).thenReturn(expectedLogs);

        List<Log> result = logJpaAdapter.getLogsByOrderId(orderId);

        Assertions.assertNotNull(result, "El resultado no debe ser nulo");
        Assertions.assertEquals(expectedLogs.size(), result.size(), "El tamaño de la lista no coincide");
        Assertions.assertSame(expectedLogs, result, "La instancia devuelta no es la esperada");
    }
}
