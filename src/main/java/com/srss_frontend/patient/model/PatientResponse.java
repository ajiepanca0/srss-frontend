package com.srss_frontend.patient.model;

import java.util.List;

import com.srss_frontend.base.model.Status;


public class PatientResponse {

	public Status status;
	
	public List<Patient> patient;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}
	
	

}
