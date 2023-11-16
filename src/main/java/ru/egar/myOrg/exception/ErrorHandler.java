package ru.egar.myOrg.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public MainServiceError dataConflict(final DataConflictException e) {
        log.info("State number is busy {}", e.getMessage());
        return MainServiceError.builder()
                .message("DataConflictException" + e.getMessage())
                .status("FORBIDDEN")
                .reason("For the requested operation the conditions are not met.")
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MainServiceError notFoundException(final NotFoundException e) {
        log.info("NotFoundException {}", e.getMessage());
        return MainServiceError.builder()
                .message("NotFoundException " + e.getMessage())
                .status("FORBIDDEN")
                .reason("For the requested operation the conditions are not met.")
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MainServiceError validException(final ValidException e) {
        log.info("ValidException {}", e.getMessage());
        return MainServiceError.builder()
                .message("ValidException " + e.getMessage())
                .status("FORBIDDEN")
                .reason("For the requested operation the conditions are not met.")
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .build();
    }


}
