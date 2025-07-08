package com.srss_frontend.outpatient.process;

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
import com.srss_frontend.outpatient.model.Outpatient;
import com.srss_frontend.outpatient.model.OutpatientResponse;

@Service
public class OutpatientProcess {

	public Logger log = LoggerFactory.getLogger(this.getClass());

	RestTemplate restTemplate = new RestTemplate(); 
	
	@Value("${srss.url}")
	String srssUrl;
	
	public OutpatientResponse getAllOutpatient() throws Exception {

		
		OutpatientResponse outpatientResponse = new OutpatientResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//ambil dari properties
		String  url = srssUrl+"/outpatient/getAll";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<OutpatientResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        OutpatientResponse.class);		
			
			if(response != null) {
				
				if(response.getBody().getStatus().getResponseCode() == 200) {
						
					outpatientResponse = response.getBody();

				}
			}
			
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 

			
		}catch (Exception e) {
	
			throw new Exception(e.getMessage());			
		}
		
		 
		 return outpatientResponse;
		
	}
	
	
	public Status saveOutpatient(Outpatient outpatient) throws Exception{

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/outpatient/add";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(outpatient, headers);
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
	
	
	public Status updateOutpatient(Outpatient outpatient) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/outpatient/update/"+outpatient.getOutpatientId();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);


		HttpEntity<?> entity = new HttpEntity<>(outpatient, headers);
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
	
	
	public OutpatientResponse getOutpatientById(Long outpatientId) throws Exception {

	
		OutpatientResponse outpatientResponse = new OutpatientResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/outpatient/getById";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("outpatientId", outpatientId);

		HttpEntity<?> entity = new HttpEntity<>( headers);
		ResponseEntity<OutpatientResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        OutpatientResponse.class);		
			
			if(response != null) {
				
				if(response.getBody().getStatus().getResponseCode() == 200) {
						
					outpatientResponse = response.getBody();
				}	
			}
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 

			
		}catch (Exception e) {
	
			throw new Exception(e.getMessage());			
		}
		
				 
		 return outpatientResponse;
		
	}
	
	
	public Status deleteOutpatientById(Long outpatientId) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/outpatient/deleteById/"+outpatientId;

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
