package com.backbase.kalah.core.game.exception;

public class KalahException extends RuntimeException{

    public KalahException(String message) {
        super(message);
    }

    public KalahException(Throwable cause) {
        super(cause);
    }

    public KalahException(String message, Throwable cause) {
        super(message, cause);
    }
}
