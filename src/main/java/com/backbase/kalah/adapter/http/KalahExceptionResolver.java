package com.backbase.kalah.adapter.http;

import com.backbase.kalah.core.game.exception.BoardValidationException;
import com.backbase.kalah.core.game.exception.FailedToJoinException;
import com.backbase.kalah.core.game.exception.GameNotFound;
import com.backbase.kalah.core.game.exception.UnauthorizedException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.time.Clock;
import java.time.OffsetDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class KalahExceptionResolver {

    @ExceptionHandler({GameNotFound.class, BoardValidationException.class, FailedToJoinException.class})
    public ResponseEntity<ApiError> handleBadRequest(Exception ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleKalahExceptions(Exception ex) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<ApiError> handleUnauthorizedExceptions(UnauthorizedException ex) {
        ApiError error = new ApiError(HttpStatus.FORBIDDEN, ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler({HttpServerErrorException.class})
    public ResponseEntity<ApiError> handleStatusException(HttpServerErrorException ex) {
        ApiError error = new ApiError(ex.getStatusCode(), ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    class ApiError {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        private OffsetDateTime timestamp;
        private HttpStatus status;
        private String error;
        private String message;

        @JsonCreator
        public ApiError(HttpStatus status, String message) {
            this.timestamp = OffsetDateTime.now(Clock.systemUTC());
            this.status = status;
            this.error = status.getReasonPhrase();
            this.message = message;
        }

        public OffsetDateTime getTimestamp() {
            return timestamp;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

    }
}
