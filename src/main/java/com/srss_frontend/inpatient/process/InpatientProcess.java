package com.srss_frontend.inpatient.process;

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
import com.srss_frontend.exception.ServiceException;
import com.srss_frontend.inpatient.model.Inpatient;
import com.srss_frontend.inpatient.model.InpatientResponse;

@Service
public class InpatientProcess {

	public Logger log = LoggerFactory.getLogger(this.getClass());

	RestTemplate restTemplate = new RestTemplate(); 
	
	@Value("${srss.url}")
	String srssUrl;
	
	public InpatientResponse getAllInpatient() throws Exception {

		
		InpatientResponse inpatientResponse = new InpatientResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//ambil dari properties
		String  url = srssUrl+"/inpatient/getAll";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<InpatientResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        InpatientResponse.class);		
			
			if(response != null) {
				
				if(response.getBody().getStatus().getResponseCode() == 200) {
						
					inpatientResponse = response.getBody();

				}
			}
			
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 

			
		}catch (Exception e) {
	
			throw new Exception(e.getMessage());			
		}
		
		 
		 return inpatientResponse;
		
	}
	
	
	public Status saveInpatient(Inpatient inpatient) throws Exception{

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/inpatient/add";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(inpatient, headers);
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
	
	
	public Status updateInpatient(Inpatient inpatient) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/inpatient/update/"+inpatient.getInpatientId();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);


		HttpEntity<?> entity = new HttpEntity<>(inpatient, headers);
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
	
	
	public InpatientResponse getInpatientById(Long inpatientId) throws Exception {

	
		InpatientResponse inpatientResponse = new InpatientResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/inpatient/getById";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("inpatientId", inpatientId);

		HttpEntity<?> entity = new HttpEntity<>( headers);
		ResponseEntity<InpatientResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        InpatientResponse.class);		
			
			if(response != null) {
				
				if(response.getBody().getStatus().getResponseCode() == 200) {
						
					inpatientResponse = response.getBody();
				}	
			}
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 

			
		}catch (Exception e) {
	
			throw new Exception(e.getMessage());			
		}
		
				 
		 return inpatientResponse;
		
	}
	
	
	public Status deleteInpatientById(Long inpatientId) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/inpatient/deleteById/"+inpatientId;

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
