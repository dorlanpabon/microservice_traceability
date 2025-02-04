package com.pragma.powerup.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.powerup.application.dto.LogRequest;
import com.pragma.powerup.application.dto.LogResponse;
import com.pragma.powerup.application.dto.LogTimeResponse;
import com.pragma.powerup.application.handler.LogHandler;
import com.pragma.powerup.infrastructure.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LogRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class LogRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LogHandler logHandler;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }

    @Test
    void testSaveLogInLog_ReturnsCreated() throws Exception {
        LogRequest request = new LogRequest();

        String jsonRequest = objectMapper.writeValueAsString(request);

        doNothing().when(logHandler).saveLogInLog(any(LogRequest.class));

        mockMvc.perform(post("/logs/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetLogFromLog_ReturnsOk() throws Exception {
        LogResponse logResponse = new LogResponse();
        List<LogResponse> expectedList = Collections.singletonList(logResponse);

        when(logHandler.getLogFromLog()).thenReturn(expectedList);

        mockMvc.perform(get("/logs/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedList)));
    }

    @Test
    void testGetLogsByOrderId_ReturnsOk() throws Exception {
        LogResponse logResponse = new LogResponse();
        List<LogResponse> expectedList = Collections.singletonList(logResponse);

        Long orderId = 1L;
        when(logHandler.getLogsByOrderId(orderId)).thenReturn(expectedList);

        mockMvc.perform(get("/logs/order/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedList)));
    }

    @Test
    void testGetLogsTimeByOrderId_ReturnsOk() throws Exception {
        LogTimeResponse logTime = new LogTimeResponse();

        Long orderId = 1L;
        when(logHandler.getLogsTimeByOrderId(orderId)).thenReturn(logTime);

        mockMvc.perform(get("/logs/order/{orderId}/time", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(logTime)));
    }

    @Test
    void testGetLogsTimeByOrders_ReturnsOk() throws Exception {
        LogTimeResponse logTime = new LogTimeResponse();
        List<LogTimeResponse> expectedList = Collections.singletonList(logTime);

        List<Long> orderIds = Arrays.asList(1L, 2L);
        when(logHandler.getLogsTimeByOrders(orderIds)).thenReturn(expectedList);

        String orderIdsParam = "1,2";

        mockMvc.perform(get("/logs/order/time")
                        .param("orderIds", orderIdsParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedList)));
    }

    @Test
    void testGetAverageTimeByOrders_ReturnsOk() throws Exception {
        Double averageTime = 12.5;
        List<Long> orderIds = Arrays.asList(1L, 2L);
        when(logHandler.getAverageTimeByOrders(orderIds)).thenReturn(averageTime);

        String orderIdsParam = "1,2";

        mockMvc.perform(get("/logs/average-time")
                        .param("orderIds", orderIdsParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(averageTime.toString()));
    }
}
