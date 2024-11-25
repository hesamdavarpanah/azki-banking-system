package com.backend.azkivam.accounts.payloads.responses;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageResponse {
    public static ResponseEntity<Object> generateResponse(String title, String message, HttpStatus status) {
        ResponseBody responseBody = new ResponseBody(title, message);

        return new ResponseEntity<>(responseBody, status);
    }

    @Getter
    record ResponseBody(String title, String message) {

    }
}
