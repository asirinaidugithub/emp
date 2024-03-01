package com.usr.entity;

import java.util.Date;

import javax.persistence.*;

import com.usr.constant.StatusEnum;
import lombok.Data;

@Data
@Entity
@Table(name = "t_user_login")
public class UserLogin extends BaseEntity {
	@Column(name = "user_id")
	private String userId;
	@Column(name = "login_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;
	@Column(name = "login_expire")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginExpire;
	@Column(name = "logout_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutTime;
	@Column(name = "status")
	private String status = StatusEnum.ACTIVE.name();
	@Column(name = "public_key")
	private String publicKey;
	@Column(name = "private_key")
	private String privateKey;

}
