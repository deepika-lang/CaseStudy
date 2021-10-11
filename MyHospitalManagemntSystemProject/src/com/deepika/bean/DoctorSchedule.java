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
public class DoctorSchedule {


	public String scheduleId;
	public String doctorId;
	public String patientId;
	public String startTime;
	public String endTime;
	
}
