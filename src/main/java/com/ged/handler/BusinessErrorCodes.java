package com.ged.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED, "No code"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Le mot de passe actuel est incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH(301, BAD_REQUEST, "Le nouveau mot de passe ne correspond pas"),
    ACCOUNT_LOCKED(302, FORBIDDEN, "Le compte de l'utilisateur est bloqué"),
    ACCOUNT_DISABLED(303, FORBIDDEN, "Le compte de l'utilisateur est désactivé"),
    BAD_CREDENTIALS(304, FORBIDDEN, "Le nom d'utilisateur et / ou le mot de passe est incorrect"),
    ;
    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.description = description;
    }
}
