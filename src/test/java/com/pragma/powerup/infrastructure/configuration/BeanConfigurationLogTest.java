package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.ILogServicePort;
import com.pragma.powerup.domain.spi.ILogPersistencePort;
import com.pragma.powerup.domain.usecase.LogUseCase;
import com.pragma.powerup.infrastructure.output.jpa.adapter.LogJpaAdapter;
import com.pragma.powerup.infrastructure.output.jpa.mapper.LogEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.ILogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

class BeanConfigurationLogTest {

    @Mock
    ILogRepository logRepository;

    @Mock
    LogEntityMapper logEntityMapper;

    @InjectMocks
    BeanConfigurationLog beanConfigurationLog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogPersistencePort() {
        ILogPersistencePort persistencePort = beanConfigurationLog.logPersistencePort();
        Assertions.assertTrue(persistencePort instanceof LogJpaAdapter, "Expected instance of LogJpaAdapter");

        LogJpaAdapter adapter = (LogJpaAdapter) persistencePort;
        Assertions.assertSame(logRepository, ReflectionTestUtils.getField(adapter, "logRepository"),
                "The logRepository should be injected correctly");
        Assertions.assertSame(logEntityMapper, ReflectionTestUtils.getField(adapter, "logEntityMapper"),
                "The logEntityMapper should be injected correctly");
    }

    @Test
    void testLogServicePort() {
        ILogServicePort servicePort = beanConfigurationLog.logServicePort();
        Assertions.assertTrue(servicePort instanceof LogUseCase, "Expected instance of LogUseCase");

        LogUseCase logUseCase = (LogUseCase) servicePort;
        Object persistencePort = ReflectionTestUtils.getField(logUseCase, "logPersistencePort");
        Assertions.assertNotNull(persistencePort, "The logPersistencePort should be injected in LogUseCase");
        Assertions.assertTrue(persistencePort instanceof LogJpaAdapter, "Expected logPersistencePort to be LogJpaAdapter");

        LogJpaAdapter adapter = (LogJpaAdapter) persistencePort;
        Assertions.assertSame(logRepository, ReflectionTestUtils.getField(adapter, "logRepository"),
                "The logRepository should be injected correctly in LogJpaAdapter inside LogUseCase");
        Assertions.assertSame(logEntityMapper, ReflectionTestUtils.getField(adapter, "logEntityMapper"),
                "The logEntityMapper should be injected correctly in LogJpaAdapter inside LogUseCase");
    }
}
