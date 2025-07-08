package com.srss_frontend.outpatient.model;

import java.util.List;

import com.srss_frontend.base.model.Status;


public class OutpatientResponse {

	public Status status;
	
	public List<Outpatient> outpatient;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Outpatient> getOutpatient() {
		return outpatient;
	}

	public void setOutpatient(List<Outpatient> outpatient) {
		this.outpatient = outpatient;
	}


}
