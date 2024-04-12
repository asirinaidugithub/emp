package com.emp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_authorities")
public class Authority extends IdEntity{
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "authority", nullable = false)
	private String authority;

}
