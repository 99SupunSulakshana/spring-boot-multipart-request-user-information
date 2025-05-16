package com.example.multipart_request_example.advisor;

import com.example.multipart_request_example.util.exceptions.NotFoundException;
import com.example.multipart_request_example.util.response.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(
                        404,
                        "Error occurred!",
                        e.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }
}
