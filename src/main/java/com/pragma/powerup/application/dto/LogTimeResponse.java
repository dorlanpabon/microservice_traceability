package com.pragma.powerup.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LogTimeResponse {

    private Long orderId;
    private Long time;

}
