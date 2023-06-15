package com.ds.project01.dto;


import com.ds.project01.constant.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
	
	private String userId;
	
	private String userPw;
	
	private String userNm;
	
	private String userEmlAddr;
	
	private String deptNo;
	
	private String userTelno;
	
	private String userAddr;
	
	private String userAprvYn="n";
	
	private Role role;
	
}
