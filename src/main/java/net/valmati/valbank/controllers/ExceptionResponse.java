package net.valmati.valbank.controllers;

import java.util.LinkedHashMap;

public class ExceptionResponse extends LinkedHashMap<String, Object> {

    ExceptionResponse(long code, String message) {
        put("code", code);
        put("message", message);
    }

    ExceptionResponse(RuntimeException ex) {
        this(ex.hashCode(), ex.getMessage());
    }
}
