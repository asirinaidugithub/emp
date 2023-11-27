package com.emp.modal;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EmployeeVo {
    private Long id;
    private String empId;
    private String firstName;
    private String lastName;
    private String email;
    private Date doj;
    private String createdBy;
    private String updatedBy;
    private Date createdDate;
    private Date updatedDate;

    private Double salary;
    private Double yearlySal;
    private Double taxAmt;
    private Double cessAmt;

    private List<String> phoneNoList;
    private List<PhoneVo> phoneNos;

}
