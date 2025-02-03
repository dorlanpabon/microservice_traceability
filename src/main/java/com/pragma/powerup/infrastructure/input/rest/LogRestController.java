package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.LogRequest;
import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.application.dto.LogTimeResponse;
import com.pragma.powerup.application.handler.LogHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @Operation(summary = "Get all logs", description = "Retrieve a list of logs")
    @GetMapping
    public ResponseEntity<Page<LogResponse>> getLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Page<LogResponse> logs = logHandler.getLogs(page, size, sortDirection);
        return ResponseEntity.ok(logs);
    }

    @Operation(summary = "Create a new log", description = "Add a new log to the system")
    @PostMapping("/")
    public ResponseEntity<Void> saveLogInLog(@RequestBody LogRequest logRequest) {
        logHandler.saveLogInLog(logRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Retrieve Log list", description = "Get all logs")
    @GetMapping("/")
    public ResponseEntity<List<LogResponse>> getLogFromLog() {
        return ResponseEntity.ok(logHandler.getLogFromLog());
    }

    @Operation(summary = "Retrieve Log by ID", description = "Get a single log by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<LogResponse> getLogFromLog(@PathVariable(name = "id") String logId) {
        return ResponseEntity.ok(logHandler.getLogFromLog(logId));
    }

    @Operation(summary = "Update log", description = "Update an existing log in the system")
    @PutMapping("/")
    public ResponseEntity<Void> updateLogInLog(@RequestBody LogRequest logRequest) {
        logHandler.updateLogInLog(logRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete log by ID", description = "Remove an existing log from the system")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogFromLog(@PathVariable String logId) {
        logHandler.deleteLogFromLog(logId);
        return ResponseEntity.noContent().build();
    }

    // Get Logs by Order ID
    @Operation(summary = "Get logs by order ID", description = "Retrieve a list of logs by order ID")
    @GetMapping("/order/{orderId}")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<LogResponse>> getLogsByOrderId(@PathVariable Long orderId) {
        List<LogResponse> logs = logHandler.getLogsByOrderId(orderId);
        return ResponseEntity.ok(logs);
    }

    @Operation(summary = "Get time in INIT and END by order ID", description = "Retrieve the time in INIT and END by order ID")
    @GetMapping("/order/{orderId}/time")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<LogTimeResponse> getLogsTimeByOrderId(@PathVariable Long orderId) {
        LogTimeResponse logTime = logHandler.getLogsTimeByOrderId(orderId);
        return ResponseEntity.ok(logTime);
    }

    //Get time in INIT and END by list of orders passed by parameter
    @Operation(summary = "Get time in INIT and END by list of orders", description = "Retrieve the time in INIT and END by list of orders")
    @GetMapping("/order/time")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<List<LogTimeResponse>> getLogsTimeByOrders(@RequestParam List<Long> orderIds) {
        List<LogTimeResponse> logTime = logHandler.getLogsTimeByOrders(orderIds);
        return ResponseEntity.ok(logTime);
    }

    // Ranking average time by list of orders passed by parameter
    @Operation(summary = "Get average time by list of orders", description = "Retrieve the average time by list of orders")
    @GetMapping("/average-time")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Double> getAverageTimeByOrders(@RequestParam List<Long> orderIds) {
        Double averageTime = logHandler.getAverageTimeByOrders(orderIds);
        return ResponseEntity.ok(averageTime);
    }
}
