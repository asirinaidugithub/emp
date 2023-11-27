package com.emp.modal;

import com.emp.entity.Employee;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class BaseResponse {
    private boolean status = Boolean.TRUE;
    private String message = "Success";
    private EmployeeVo employee;
    public BaseResponse(Exception e) {
        this.status = Boolean.FALSE;
        this.message = e.getMessage();
    }

}
