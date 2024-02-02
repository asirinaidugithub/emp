package com.emp.entity;

import com.emp.modal.PhoneVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "t_phone")
public class Phone extends BaseEntity {
    @Column(name = "type")
    private String type;
    @Column(name = "phone_no")
    private String phoneNo;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public Phone(PhoneVo phone, Employee employee) {
        this.type = phone.getType();
        this.phoneNo = phone.getPhoneNo();
        this.employee = employee;
        this.setCreatedBy(employee.getCreatedBy());
        this.setUpdatedBy(employee.getUpdatedBy());
        this.setCreatedDate(employee.getCreatedDate());
        this.setUpdatedDate(employee.getUpdatedDate());
    }
}
