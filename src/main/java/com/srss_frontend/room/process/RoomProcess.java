package com.srss_frontend.room.process;

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
import com.srss_frontend.room.model.Room;
import com.srss_frontend.room.model.RoomResponse;




@Service
public class RoomProcess {

	public Logger log = LoggerFactory.getLogger(this.getClass());

	RestTemplate restTemplate = new RestTemplate(); 
	
	@Value("${srss.url}")
	String srssUrl;
	
	public RoomResponse getAllRoom() throws Exception {

		
		RoomResponse roomResponse = new RoomResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//ambil dari properties
		String  url = srssUrl+"/room/getAll";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<RoomResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        RoomResponse.class);		
			
			if(response != null) {
				
				if(response.getBody().getStatus().getResponseCode() == 200) {
						
					roomResponse = response.getBody();

				}
			}
			
			
		}catch(HttpClientErrorException | HttpServerErrorException he) {
			
			throw new ServiceException(he.getResponseBodyAs(Status.class).getResponseMessage()); 

			
		}catch (Exception e) {
	
			throw new Exception(e.getMessage());			
		}
		
		 
		 return roomResponse;
		
	}
	
	
	public Status saveRoom(Room room) throws Exception{

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/room/add";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		HttpEntity<?> entity = new HttpEntity<>(room, headers);
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
	
	
	public Status updateRoom(Room room) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/room/update/"+room.getIdRoom();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);


		HttpEntity<?> entity = new HttpEntity<>(room, headers);
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
	
	
	public RoomResponse getRoomById(Long roomId) throws Exception {

	
		RoomResponse patient = new RoomResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/room/getById";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		builder.queryParam("roomId", roomId);

		HttpEntity<?> entity = new HttpEntity<>( headers);
		ResponseEntity<RoomResponse> response = null;

		
		try {
			response = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        RoomResponse.class);		
			
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
	
	
	public Status deleteRoomById(Long roomId) throws Exception {

		
		Status status = new Status();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String  url = srssUrl+"/room/deleteById/"+roomId;

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
