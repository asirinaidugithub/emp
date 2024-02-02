package com.emp.controller;

import com.emp.modal.BaseResponse;
import com.emp.modal.EmployeeVo;
import com.emp.service.EmpService;
import com.emp.util.AppException;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/emp")
public class EmpController {
	@Autowired
	EmpService empService;

	@GetMapping(value = "/get-emp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getEmp(@RequestParam("empId") Long empid) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(empService.getEmp(empid));
		return genResponse(baseResponse);
	}

	@GetMapping(value = "/get-emps", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getEmps() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(empService.getEmpList());
		return genResponse(baseResponse);
	}

	@PostMapping(value = "/save-emp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveEmp(@RequestBody EmployeeVo employeeVo) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(empService.saveEmp(employeeVo));
		return genResponse(baseResponse);
	}

	@PostMapping(value = "/update-emp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmp(@RequestBody EmployeeVo employeeVo) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(empService.updateEmp(employeeVo));
		return genResponse(baseResponse);
	}

	@DeleteMapping(value = "/delete-emp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteEmp(@RequestParam("empId") Long empId) {
		BaseResponse baseResponse = new BaseResponse();
		empService.deleteEmp(empId);
		return genResponse(baseResponse);
	}

	private void updateRes(BaseResponse baseRes, Exception e) {
		String message = null;
		if (e instanceof AppException) {
			AppException appEx = (AppException) e;
			if (Objects.nonNull(appEx.getErrMap())) {
				message = appEx.getErrMap().toString();
			} else {
				message = e.getMessage();
			}
		} else {
			message = e.getMessage();
		}
		baseRes.setStatus(Boolean.FALSE);
		baseRes.setMessage(message);
	}

	private ResponseEntity<String> genResponse(BaseResponse baseResponse) {
		String resStr = new GsonBuilder().create().toJson(baseResponse);
		if (baseResponse.isStatus()) {
			return new ResponseEntity<>(resStr, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(resStr, HttpStatus.BAD_REQUEST);
		}
	}
}
