package com.emp.config;

import com.emp.modal.BaseResponse;
import com.emp.util.AppException;
import com.emp.util.EmpException;
import com.emp.util.PhoneException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AppExceptionController {
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> empException(Exception exception) {
		log.error("Error occured", exception);
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatus(Boolean.FALSE);
		baseResponse.setCode(100);
		baseResponse.setMessage(exception.getMessage());

		return new ResponseEntity<>(baseResponse, HttpStatus.NOT_FOUND);
	}

}
