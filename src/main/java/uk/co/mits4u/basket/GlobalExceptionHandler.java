package uk.co.mits4u.basket;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uk.co.mits4u.basket.api.ExceptionData;
import uk.co.mits4u.basket.api.ItemName;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Could not parse input. ");
        sb.append("Allowable values for item names are: [" + StringUtils.join(ItemName.values(), ",") + "]. ");
        sb.append("Input is expected in JSON format.");
        return new ResponseEntity<>(new ExceptionData(sb.toString()), headers, status);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleGenericException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ExceptionData(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

