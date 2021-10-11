package com.deepika.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Patient {

	//private String patient_id;
	private String patient_name;
	private String city;
	private String gender;
	private String phone_number;
	private int height_in_cm;
	private double weight_in_kg;
	private String username;
	private String password;
}
