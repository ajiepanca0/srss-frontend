package com.srss_frontend.patient.process;

import java.lang.reflect.Type;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.srss_frontend.base.model.Status;
import com.srss_frontend.exception.ServiceException;
import com.srss_frontend.patient.model.Patient;
import com.srss_frontend.patient.model.PatientResponse;




@Service
public class PatientProcess {

	public Logger log = LoggerFactory.getLogger(this.getClass());

	RestTemplate restTemplate = new RestTemplate(); 
	
	@Value("${srss.url}")
	String srssUrl;
	
	public PatientResponse getAllPatient() throws Exception {

		
		PatientResponse patientResponse = new PatientResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//ambil dari properties
		String  url = srssUrl+"/patient/getAll";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<PatientResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        PatientResponse.class);		
			
			if(response != null) {
				
				if(response.getBody().getStatus().getResponseCode() == 200) {
						
					patientResponse = response.getBody();

				}
			}
			
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 

			
		}catch (Exception e) {
	
			throw new Exception(e.getMessage());			
		}
		
		 
		 return patientResponse;
		
	}
	
	
	public Status savePatient(Patient patient) throws Exception{

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/patient/add";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(patient, headers);
		ResponseEntity<Status> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.POST, 
			        entity, 
			        Status.class);
						
			
			if(response != null) {
				
				if(response.getBody().getResponseCode() == 200) {
					
					status = response.getBody();
				}
			}
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 
			
		}catch (Exception e) {
			

			throw new Exception(e.getMessage()); 

		}
		
		 return status;
		
	}
	
	
	public Status updatePatient(Patient patient) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/patient/update/"+patient.getIdPasien();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);


		HttpEntity<?> entity = new HttpEntity<>(patient, headers);
		ResponseEntity<Status> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.PUT, 
			        entity, 
			        Status.class);
			
			
			
			if(response != null) {
				
				if(response.getBody().getResponseCode() == 200) {
					
					status = response.getBody();
					
				}				 				 
			}
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 
			
		}catch (Exception e) {
			
			throw new Exception(e.getMessage()); 

		}
		
		 
		 return status;
		
	}
	
	
	public PatientResponse getPatientById(Long idPatient) throws Exception {

	
		PatientResponse patient = new PatientResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/patient/getById";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("patientId", idPatient);

		HttpEntity<?> entity = new HttpEntity<>( headers);
		ResponseEntity<PatientResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        PatientResponse.class);		
			
			if(response != null) {
				
				if(response.getBody().getStatus().getResponseCode() == 200) {
						
						patient = response.getBody();
				}	
			}
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 

			
		}catch (Exception e) {
	
			throw new Exception(e.getMessage());			
		}
		
				 
		 return patient;
		
	}
	
	
	public Status deletePatientById(Long idPatient) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/patient/deleteById/"+idPatient;

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>( headers);
		ResponseEntity<Status> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.DELETE, 
			        entity, 
			        Status.class);
			
			if(response != null) {
				
				if(response.getBody().getResponseCode() == 200 ) {
					
					status = response.getBody();

				}
				 				 
			}
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 
			
		}catch (Exception e) {
			
			throw new Exception(e.getMessage()); 

		}
		
		 return status;
		
	}
	
}
