package com.deepika.presentation;

import java.util.Collection;
import java.util.List;

public interface DoctorSchedulePresentation {
	
	int showDoctorAvailability(String complaint);
	int SlotAvailability(int doctorId,Collection<String> slotList,String username);
}
