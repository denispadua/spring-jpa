package com.ecommerce.ecommercejpa.Role;

public enum RoleEnum {
    USER(5L),
    MOD(6L);

    private final Long value;

    RoleEnum(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
