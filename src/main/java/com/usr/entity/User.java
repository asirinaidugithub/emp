package com.usr.entity;

import com.usr.constant.StatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "t_user")
public class User extends BaseEntity {
	@Column(name = "user_id")
	private String userId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email")
	private String email;
	@Column(name = "doj")
	@Temporal(TemporalType.DATE)
	private Date doj;
	@Column(name = "status")
	private String status = StatusEnum.NOT_ACTIVE.name();
	@Column(name = "qr_key")
	private String qrKey;
	@Column(name = "qr_code")
	private String qrCode;
	@OneToMany(mappedBy = "user")
	private List<Phone> phones;

}
