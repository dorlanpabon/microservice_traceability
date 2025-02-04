package com.pragma.powerup.domain.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderStatusEnumTest {

    @Test
    void testEnumValues() {
        OrderStatusEnum[] expected = {
                OrderStatusEnum.PENDING,
                OrderStatusEnum.IN_PREPARATION,
                OrderStatusEnum.READY,
                OrderStatusEnum.DELIVERED,
                OrderStatusEnum.CANCELED
        };
        Assertions.assertArrayEquals(expected, OrderStatusEnum.values(),
                "Los valores del enum no coinciden con los esperados");
    }

    @Test
    void testValueOf() {
        Assertions.assertEquals(OrderStatusEnum.PENDING, OrderStatusEnum.valueOf("PENDING"));
        Assertions.assertEquals(OrderStatusEnum.IN_PREPARATION, OrderStatusEnum.valueOf("IN_PREPARATION"));
        Assertions.assertEquals(OrderStatusEnum.READY, OrderStatusEnum.valueOf("READY"));
        Assertions.assertEquals(OrderStatusEnum.DELIVERED, OrderStatusEnum.valueOf("DELIVERED"));
        Assertions.assertEquals(OrderStatusEnum.CANCELED, OrderStatusEnum.valueOf("CANCELED"));
    }
}
