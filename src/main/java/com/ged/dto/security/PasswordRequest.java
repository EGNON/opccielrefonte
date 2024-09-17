package com.ged.dto.security;

import lombok.Data;

@Data
public class PasswordRequest {
    private String username;
    private String oldPassword;
    private String newPassword;
}
