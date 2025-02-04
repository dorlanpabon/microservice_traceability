package com.pragma.powerup.application.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LogRequestTest {

    @Mock
    private LogRequest logRequest;

    @BeforeEach
    public void setUp() {
        logRequest = new LogRequest();
        logRequest.setId("ASDF1234");
        logRequest.setType("INFO");
        logRequest.setTimestamp(LocalDateTime.now());
        logRequest.setClientId(1L);
        logRequest.setOrderId(1L);
        logRequest.setRestaurantId(1L);
    }

    @Test
    void testGetId() {
        String result = logRequest.getId();

        assertEquals("ASDF1234", result);
    }

    @Test
    void testGetType() {
        String result = logRequest.getType();

        assertEquals("INFO", result);
    }

    @Test
    void testGetTimestamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        logRequest.setTimestamp(localDateTime);

        assertEquals(localDateTime, logRequest.getTimestamp());
    }

    @Test
    void testGetClientId() {
        Long result = logRequest.getClientId();

        assertEquals(1L, result);
    }

    @Test
    void testGetOrderId() {
        Long result = logRequest.getOrderId();

        assertEquals(1L, result);
    }

    @Test
    void testGetRestaurantId() {
        Long result = logRequest.getRestaurantId();

        assertEquals(1L, result);
    }

}
