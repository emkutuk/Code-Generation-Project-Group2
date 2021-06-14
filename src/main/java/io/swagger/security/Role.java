package io.swagger.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority
{
    EMPLOYEE,
    CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
