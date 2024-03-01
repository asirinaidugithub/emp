package com.usr.controller;

import com.usr.modal.BaseResponse;
import com.usr.modal.UserVo;
import com.usr.service.UserService;
import com.usr.util.AppException;
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
@RequestMapping(value = "/usr")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(value = "/get-usr", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getEmp(@RequestParam("usrId") Long empid) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(userService.getUser(empid));
		return genResponse(baseResponse);
	}

	@GetMapping(value = "/get-usrs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getEmps() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(userService.getUserList());
		return genResponse(baseResponse);
	}

	@PutMapping(value = "/save-usr", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveEmp(@RequestBody UserVo userVo) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(userService.saveUser(userVo));
		return genResponse(baseResponse);
	}

	@PostMapping(value = "/update-usr", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmp(@RequestBody UserVo userVo) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setData(userService.updateUser(userVo));
		return genResponse(baseResponse);
	}

	@DeleteMapping(value = "/delete-usr", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteEmp(@RequestParam("usrId") Long userId) {
		BaseResponse baseResponse = new BaseResponse();
		userService.deleteUser(userId);
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
