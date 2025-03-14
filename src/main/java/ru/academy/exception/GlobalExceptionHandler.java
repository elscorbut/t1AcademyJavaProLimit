package ru.academy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.academy.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LIMIT_EXCEEDED = "LIMIT_EXCEEDED";
    private static final String ILLEGAL_LIMIT_VALUE = "ILLEGAL_LIMIT_VALUE";
    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    private static final String INCORRECT_LIMIT_RESTORE_VALUE = "INCORRECT_LIMIT_RESTORE_VALUE";
    private static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";

    @ExceptionHandler(LimitExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleLimitExceededException(LimitExceededException ex) {
        return new ErrorResponseDto(
                LIMIT_EXCEEDED,
                ex.getMessage()
        );
    }

    @ExceptionHandler(IllegalLimitValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleIllegalDefaultLimitException(IllegalLimitValueException ex) {
        return new ErrorResponseDto(
                ILLEGAL_LIMIT_VALUE,
                ex.getMessage()
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorResponseDto(
                USER_NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(IncorrectLimitRestoreValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleLimitRestorationException(IncorrectLimitRestoreValueException ex) {
        return new ErrorResponseDto(
                INCORRECT_LIMIT_RESTORE_VALUE,
                ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleUnknownError(Exception ex) {
        return new ErrorResponseDto(
                UNKNOWN_ERROR,
                ex.getMessage()
        );
    }
}
