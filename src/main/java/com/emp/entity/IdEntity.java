package com.emp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class IdEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
