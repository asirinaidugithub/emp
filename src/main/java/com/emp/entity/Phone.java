package com.emp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "t_phone")
public class Phone {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "phone_no")
    private String phoneNo;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public Phone(String phoneNo, Employee employee){
        this.phoneNo=phoneNo;
        this.employee=employee;
    }
}
