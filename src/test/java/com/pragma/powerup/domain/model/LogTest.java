package com.pragma.powerup.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class LogTest {

    @Mock
    Log log;

    @BeforeEach
    void setUp() {
        log = new Log();
        log.setId("ASDF");
        log.setType("INFO");
        log.setTime(1000L);
        log.setOrderId(1L);
        log.setClientId(1L);
        log.setRestaurantId(1L);
        log.setTimestamp(LocalDateTime.now());
    }

    @Test
    void testGetId() {
        String result = log.getId();
        assertEquals("ASDF", result);
    }

    @Test
    void testGetType() {
        String result = log.getType();
        assertEquals("INFO", result);
    }

    @Test
    void testGetTime() {
        Long result = log.getTime();
        assertEquals(1000L, result);
    }

    @Test
    void testGetOrderId() {
        Long result = log.getOrderId();
        assertEquals(1L, result);
    }

    @Test
    void testGetClientId() {
        Long result = log.getClientId();
        assertEquals(1L, result);
    }

    @Test
    void testGetRestaurantId() {
        Long result = log.getRestaurantId();
        assertEquals(1L, result);
    }

    @Test
    void testGetTimestamp() {
        LocalDateTime result = LocalDateTime.now();
        log.setTimestamp(result);
        assertEquals(result, log.getTimestamp());
    }

    @Test
    void testSetType() {
        log.setType("ERROR");
        String result = log.getType();
        assertEquals("ERROR", result);
    }

}
