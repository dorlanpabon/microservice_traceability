package com.pragma.powerup.infrastructure.output.jpa.repository;

import com.pragma.powerup.infrastructure.output.jpa.entity.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ILogRepository extends MongoRepository<LogEntity, String> {

    Optional<LogEntity> findById(String logId);

    void deleteById(String logId);

    List<LogEntity> findByOrderId(Long orderId);

    List<LogEntity> findByOrderIdIn(List<Long> orderIds);
}
