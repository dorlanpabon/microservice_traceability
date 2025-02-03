package com.pragma.powerup.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class LogRequest {

    private String id;

    private String type;

    private Long clientId;

    private Long orderId;

    private Long restaurantId;

    private LocalDateTime timestamp;

}
