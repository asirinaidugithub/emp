package com.emp.modal;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EmpVo {
	private Long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;

}
