package com.cts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLogin {

	private String userName;
	private String password;
	private String role;
}
