package com.coviam.sample.model;

/**
 * @author waseem.khan since 2019-08-19.
 */
public enum Error {

    SYSTEM_PARAMETER_NULL("systemParameter should not be null"),
    SYSTEM_PARAMETER_NOT_FOUND("systemParameter could not be found"),
    SYSTEM_PARAMETER_NOT_SAVED("systemParameter could not be saved"),
    SYSTEM_PARAMETER_NOT_DELETED("systemParameter could not be deleted"),
    SYSTEM_PARAMETER_NOT_UPDATED("systemParameter could not be updated"),
    SYSTEM_PARAMETER_ALREADY_EXIST("systemParameter parameter already exist"),
    SYSTEM_PARAMETER_NAME_NULL("system parameter name should not be null"),

    SYSTEM_ERROR("Internal system error");

    private String message;

    Error(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }

    public String getMessage() {
        return this.message;
    }
}
