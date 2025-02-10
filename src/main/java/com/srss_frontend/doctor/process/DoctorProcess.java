package com.srss_frontend.doctor.process;

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

import com.srss_frontend.base.model.Status;
import com.srss_frontend.doctor.model.Doctor;
import com.srss_frontend.doctor.model.DoctorResponse;
import com.srss_frontend.exception.ServiceException;




@Service
public class DoctorProcess {

	public Logger log = LoggerFactory.getLogger(this.getClass());

	RestTemplate restTemplate = new RestTemplate(); 
	
	@Value("${srss.url}")
	String srssUrl;
	
	public DoctorResponse getAllDoctor() throws Exception {

		
		DoctorResponse doctorResponse = new DoctorResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//ambil dari properties
		String  url = srssUrl+"/doctor/getAll";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<DoctorResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        DoctorResponse.class);		
			
			if(response != null) {
				
				if(response.getBody().getStatus().getResponseCode() == 200) {
						
					doctorResponse = response.getBody();

				}
			}
			
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 

			
		}catch (Exception e) {
	
			throw new Exception(e.getMessage());			
		}
		
		 
		 return doctorResponse;
		
	}
	
	
	public Status saveDoctor(Doctor doctor) throws Exception{

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/doctor/add";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(doctor, headers);
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
	
	
	public Status updateDoctor(Doctor doctor) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/doctor/update/"+doctor.getIdDokter();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);


		HttpEntity<?> entity = new HttpEntity<>(doctor, headers);
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
	
	
	public DoctorResponse getDoctorById(Long idDoctor) throws Exception {

	
		DoctorResponse doctorResponse = new DoctorResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/doctor/getById";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("doctorId", idDoctor);

		HttpEntity<?> entity = new HttpEntity<>( headers);
		ResponseEntity<DoctorResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        DoctorResponse.class);		
			
			if(response != null) {
				
				if(response.getBody().getStatus().getResponseCode() == 200) {
						
					doctorResponse = response.getBody();
				}	
			}
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 

			
		}catch (Exception e) {
	
			throw new Exception(e.getMessage());			
		}
		
				 
		 return doctorResponse;
		
	}
	
	
	public Status deleteDoctorById(Long idDoctor) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/doctor/deleteById/"+idDoctor;

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
