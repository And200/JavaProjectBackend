package com.example.example.web.rest.errors;


import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {


    //this is for your common error Customize HandleException, there`s more method in case that you don't want

    // customize the commons Error , in that case use the other methods of the Class ResponseEntityExceptionHandler
    //, for example Media Type not Supported defendant not encaused methods
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

       //AUTO GENERATED METHOD STUB
        /*
        return super.handleExceptionInternal(ex, body, headers, status, request);

         */
        ConstanstError constanstError=new ConstanstError(status,ex.getMessage());
        return ResponseEntity.status(status).headers(headers).body(constanstError);

    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ConstanstError> handleProductNotFound(ProductNotFoundException exception){
        /*
        ConstanstError constanstError=new ConstanstError();
        constanstError.setStatus(HttpStatus.NOT_FOUND);
        constanstError.setDateTime(LocalDateTime.now());
        constanstError.setMessage(exception.getMessage());

        return ResponseEntity.status(constanstError.getStatus()).body(constanstError);


         */
        ConstanstError constanstError=new ConstanstError(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(constanstError.getStatus()).body(constanstError);
    }

    /* not is neccesary this method due to implemented method HandleExceptionInternal

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ConstanstError>handleJsonMappingException(JsonMappingException exception){
        ConstanstError constanstError=new ConstanstError(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(constanstError);
    }
     */
}
