package com.usr.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class IdEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
