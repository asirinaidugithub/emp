package com.emp.config;

import com.emp.modal.BaseResponse;
import com.emp.util.EmpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = EmpException.class)
    public ResponseEntity<Object> handleEmpException(EmpException e) {
        log.error("Warning: {}", e.getMessage());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(Boolean.FALSE);
        baseResponse.setCode(100);
        baseResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error("Error : {}", e.getMessage(), e);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(Boolean.FALSE);
        baseResponse.setCode(101);
        baseResponse.setMessage("Please contact system admin");
        return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
    }

}
