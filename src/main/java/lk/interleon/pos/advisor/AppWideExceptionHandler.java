package lk.interleon.pos.advisor;

import lk.interleon.pos.utility.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public ResponseUtil handleAllRuntimeExceptions(RuntimeException e) {
        return new ResponseUtil("Error", e.getMessage(), null);
    }
}
