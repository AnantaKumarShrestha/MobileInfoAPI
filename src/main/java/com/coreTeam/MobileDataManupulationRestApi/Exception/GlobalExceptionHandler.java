package com.coreTeam.MobileDataManupulationRestApi.Exception;


import com.coreTeam.MobileDataManupulationRestApi.Model.ErrorModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler({MobileNotFoundException.class,OwnerNotFoundException.class})
    public ResponseEntity<ErrorModel> handleMethodArgumentNotValid(MobileNotFoundException e){
      String message=e.getMessage();
      String https=Integer.toString(HttpStatus.NOT_FOUND.value());
      ErrorModel errorModel=new ErrorModel(message,https);
      return  new ResponseEntity<>(errorModel,HttpStatus.NOT_FOUND);
    }


}
