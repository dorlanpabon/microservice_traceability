package com.pragma.powerup.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogResponse {

    private String logId;

    private String logType;

    private Long logClientId;

    private Long logOrderId;

    private Long logRestaurantId;

    private LocalDateTime logTimestamp;

}
