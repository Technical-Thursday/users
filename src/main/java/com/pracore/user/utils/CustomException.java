package com.pracore.user.utils;

public class CustomException extends RuntimeException {
    public CustomException() {
        super("No logic found");
    }

    public CustomException(String errprMesage) {
        super(errprMesage);
    }
}
