package com.emp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_employee")
public class Employee extends BaseEntity {
    @Column(name = "emp_id")
    private String empId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "doj")
    @Temporal(TemporalType.DATE)
    private Date doj;
    @Column(name = "salary")
    private Double salary;
    @OneToMany(mappedBy = "employee")
    private List<Phone> phones;

}
