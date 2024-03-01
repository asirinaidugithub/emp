package com.usr.entity;

import com.usr.modal.PhoneVo;
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
    @JoinColumn(name = "user_id")
    private User user;

    public Phone(PhoneVo phone, User user) {
        this.type = phone.getType();
        this.phoneNo = phone.getPhoneNo();
        this.user = user;
        this.setCreatedBy(user.getCreatedBy());
        this.setUpdatedBy(user.getUpdatedBy());
        this.setCreatedDate(user.getCreatedDate());
        this.setUpdatedDate(user.getUpdatedDate());
    }
}
