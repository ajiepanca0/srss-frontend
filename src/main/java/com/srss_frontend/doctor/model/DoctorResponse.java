package com.srss_frontend.doctor.model;

import java.util.List;

import com.srss_frontend.base.model.Status;


public class DoctorResponse {

	public Status status;
	
	public List<Doctor> doctor;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Doctor> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<Doctor> doctor) {
		this.doctor = doctor;
	}

	
	
	

}
