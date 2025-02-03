package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.LogRequest;
import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.application.dto.LogTimeResponse;
import com.pragma.powerup.application.handler.LogHandler;
import com.pragma.powerup.infrastructure.constants.InfrastructureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Log API")
@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogRestController {

    private final LogHandler logHandler;

    @Operation(summary = "Create a new log", description = "Add a new log to the system")
    @PostMapping("/")
    @PreAuthorize(InfrastructureConstants.HAS_ROLE_EMPLOYEE_OR_CLIENT)
    public ResponseEntity<Void> saveLogInLog(@RequestBody LogRequest logRequest) {
        logHandler.saveLogInLog(logRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Retrieve Log list", description = "Get all logs")
    @GetMapping("/")
    public ResponseEntity<List<LogResponse>> getLogFromLog() {
        return ResponseEntity.ok(logHandler.getLogFromLog());
    }

    @Operation(summary = "Get logs by order ID", description = "Retrieve a list of logs by order ID")
    @GetMapping("/order/{orderId}")
    @PreAuthorize(InfrastructureConstants.HAS_ROLE_CLIENT)
    public ResponseEntity<List<LogResponse>> getLogsByOrderId(@PathVariable Long orderId) {
        List<LogResponse> logs = logHandler.getLogsByOrderId(orderId);
        return ResponseEntity.ok(logs);
    }

    @Operation(summary = "Get time in INIT and END by order ID", description = "Retrieve the time in INIT and END by order ID")
    @GetMapping("/order/{orderId}/time")
    @PreAuthorize(InfrastructureConstants.HAS_ROLE_OWNER)
    public ResponseEntity<LogTimeResponse> getLogsTimeByOrderId(@PathVariable Long orderId) {
        LogTimeResponse logTime = logHandler.getLogsTimeByOrderId(orderId);
        return ResponseEntity.ok(logTime);
    }

    @Operation(summary = "Get time in INIT and END by list of orders", description = "Retrieve the time in INIT and END by list of orders")
    @GetMapping("/order/time")
    @PreAuthorize(InfrastructureConstants.HAS_ROLE_OWNER)
    public ResponseEntity<List<LogTimeResponse>> getLogsTimeByOrders(@RequestParam List<Long> orderIds) {
        List<LogTimeResponse> logTime = logHandler.getLogsTimeByOrders(orderIds);
        return ResponseEntity.ok(logTime);
    }

    @Operation(summary = "Get average time by list of orders", description = "Retrieve the average time by list of orders")
    @GetMapping("/average-time")
    @PreAuthorize(InfrastructureConstants.HAS_ROLE_OWNER)
    public ResponseEntity<Double> getAverageTimeByOrders(@RequestParam List<Long> orderIds) {
        Double averageTime = logHandler.getAverageTimeByOrders(orderIds);
        return ResponseEntity.ok(averageTime);
    }
}
