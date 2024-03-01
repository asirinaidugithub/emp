package com.usr.modal;

import lombok.*;

@Data
@NoArgsConstructor
public class BaseResponse<T> {
	private Integer code = 0;
	private boolean status = Boolean.TRUE;
	private String message = "Success";
	private T data;
	public BaseResponse(Exception e) {
		this.status = Boolean.FALSE;
		this.message = e.getMessage();
	}

}
