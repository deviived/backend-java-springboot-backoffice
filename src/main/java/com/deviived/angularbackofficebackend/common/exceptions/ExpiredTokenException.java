package com.deviived.angularbackofficebackend.common.exceptions;

public class ExpiredTokenException extends RuntimeException {
    public ExpiredTokenException(String msg) {
        super(msg);
    }
}
