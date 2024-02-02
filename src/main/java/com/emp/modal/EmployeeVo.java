package com.emp.modal;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeVo {
    private Long id;
    private String empId;
    private String firstName;
    private String lastName;
    private String email;
    private Date doj;
    private Double salary;
    private List<PhoneVo> phones;

}
