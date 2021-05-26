package com.ktds.devpro.newcomer.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MyUserVO {
	private String uid;
	private String password;
	private String email;
	private char gender;
	private int	age;
	private String housingtype;
}
