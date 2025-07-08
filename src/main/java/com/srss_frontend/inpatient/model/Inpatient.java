package com.srss_frontend.inpatient.model;

import java.time.LocalDate;

import com.srss_frontend.doctor.model.Doctor;
import com.srss_frontend.patient.model.Patient;
import com.srss_frontend.room.model.Room;


public class Inpatient {

	private Long inpatientId;

	private String inpatientNumber;

	private LocalDate admissionDate;

	private Patient patient;

	private Doctor doctor;

	private Room room;

	private String diagnosis;

	private String description;

	public Long getInpatientId() {
		return inpatientId;
	}

	public void setInpatientId(Long inpatientId) {
		this.inpatientId = inpatientId;
	}

	public String getInpatientNumber() {
		return inpatientNumber;
	}

	public void setInpatientNumber(String inpatientNumber) {
		this.inpatientNumber = inpatientNumber;
	}

	public LocalDate getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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
