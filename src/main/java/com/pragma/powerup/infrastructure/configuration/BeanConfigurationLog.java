package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.ILogServicePort;
import com.pragma.powerup.domain.model.Log;
import com.pragma.powerup.domain.spi.ILogPersistencePort;
import com.pragma.powerup.domain.usecase.LogUseCase;
import com.pragma.powerup.infrastructure.output.jpa.adapter.LogJpaAdapter;
import com.pragma.powerup.infrastructure.output.jpa.mapper.LogEntityMapper;
import com.pragma.powerup.infrastructure.output.jpa.repository.ILogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationLog {

    private final ILogRepository logRepository;
    private final LogEntityMapper logEntityMapper;

    @Bean
    public ILogPersistencePort logPersistencePort() {
        return new LogJpaAdapter(logRepository, logEntityMapper);
    }

    @Bean
    public ILogServicePort logServicePort() {
        return new LogUseCase(logPersistencePort()) {
            @Override
            public Page<Log> getLogs(int page, int size, boolean ascending) {
                return null;
            }
        };
    }
}
