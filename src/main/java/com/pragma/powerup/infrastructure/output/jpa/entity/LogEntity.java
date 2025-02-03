package com.pragma.powerup.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "logs") // Nombre de la colección en MongoDB
public class LogEntity {

    @Id
    @Field(name = "id")
    private String id;

    @Field(name = "type")
    private String type;

    @Field(name = "client_id")
    private Long clientId;

    @Field(name = "order_id")
    private Long orderId;

    @Field(name = "restaurant_id")
    private Long restaurantId;

    @Field(name = "timestamp")
    private LocalDateTime timestamp;
}
