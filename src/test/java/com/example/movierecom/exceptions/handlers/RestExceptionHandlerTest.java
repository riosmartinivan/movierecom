package com.example.movierecom.exceptions.handlers;

import com.example.movierecom.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RestExceptionHandlerTest {

    @Test
    void handleBadRequestException() {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Test");

        RestExceptionHandler handler = new RestExceptionHandler();
        BadRequestException badRequestException = new BadRequestException("Test");

        ResponseEntity<Object> error = handler.handleBadRequestException(badRequestException);
        assertNotNull(error.getBody(), () -> "The body should not be null");

        String errorMessage = ((ApiError) error.getBody()).getMessage();
        assertEquals(errorMessage, apiError.getMessage());
        assertEquals(error.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}