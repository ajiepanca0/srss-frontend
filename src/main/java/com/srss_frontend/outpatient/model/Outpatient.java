package com.srss_frontend.outpatient.model;

import java.time.LocalDate;

import com.srss_frontend.doctor.model.Doctor;
import com.srss_frontend.patient.model.Patient;

public class Outpatient {

	private Long outpatientId;

	private String outpatientNumber;

	private LocalDate appointmentDate;

	private Patient patient;
	
	private Doctor doctor;

	private String diagnosis;

	private String description;

	public Long getOutpatientId() {
		return outpatientId;
	}

	public void setOutpatientId(Long outpatientId) {
		this.outpatientId = outpatientId;
	}

	public String getOutpatientNumber() {
		return outpatientNumber;
	}

	public void setOutpatientNumber(String outpatientNumber) {
		this.outpatientNumber = outpatientNumber;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
