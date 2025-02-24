package com.project.commentsapp.common.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuthUser {
    private String username;
    private Set<String> roles;
    private boolean isAdmin;

    public AuthUser(String username, Set<String> roles) {
        this.username = username;
        this.roles = roles;
        this.isAdmin = roles.contains("ROLE_ADMIN");
    }
}
