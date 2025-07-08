package com.srss_frontend.inpatient.model;

import java.util.List;

import com.srss_frontend.base.model.Status;


public class InpatientResponse {

	public Status status;
	
	public List<Inpatient> inpatient;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Inpatient> getInpatient() {
		return inpatient;
	}

	public void setInpatient(List<Inpatient> inpatient) {
		this.inpatient = inpatient;
	}

}
