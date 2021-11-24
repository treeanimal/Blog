package com.mycompany.white.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class Exception {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
