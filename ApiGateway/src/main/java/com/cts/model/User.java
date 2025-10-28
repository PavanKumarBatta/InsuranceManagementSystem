package com.cts.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=true)
@Table(name="users")
public class User {

	@Id
	private Long id;
	@Column("userName")
	private String userName;
	private String password;
	private String role;
}
