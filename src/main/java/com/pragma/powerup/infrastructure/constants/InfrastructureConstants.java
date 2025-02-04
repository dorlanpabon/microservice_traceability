package com.pragma.powerup.infrastructure.constants;

public class InfrastructureConstants {

    public static final String HAS_ROLE_EMPLOYEE_OR_CLIENT = "hasAnyRole('EMPLOYEE','CLIENT')";
    public static final String HAS_ROLE_CLIENT = "hasRole('CLIENT')";
    public static final String HAS_ROLE_OWNER = "hasRole('OWNER')";

    InfrastructureConstants() {
        throw new IllegalStateException("Utility class");
    }
}
