package com.deepika.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//POJO :Plain Old Java Object / JavaBean
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Doctor {

	//public String doctor_id;
	private String doctor_name;
	private String city;
	private String gender;
	private String education;
	private String specialization;
	private String emergency_number;
	private String complaint;
	private String username;
	private String password;
	private String starttime;
	private String endtime;
	
}