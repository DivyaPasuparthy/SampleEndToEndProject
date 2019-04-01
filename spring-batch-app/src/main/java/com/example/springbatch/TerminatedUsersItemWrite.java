package com.example.springbatch;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.springbatch.model.TerminatedUsers;
import com.example.springbatch.util.RestTemplateResponseErrorHandler;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class TerminatedUsersItemWrite implements ItemWriter<TerminatedUsers>{
	
	private static final Logger log = LoggerFactory.getLogger(TerminatedUsersItemWrite.class);
	
	@Autowired
	@Qualifier("restTemplate")
	public RestTemplate restTemplate;

	final String URI_TO_UPDATE_USER_STATUS = "http://localhost:8085/api/users/{username}";
	
	/**
	 * This method makes a REST API call (POST) to update the user status to suspended for the 
	 * user present in terminated users.
	 */
	@Override
	public void write(List<? extends TerminatedUsers> terminatedUsers) throws JsonProcessingException{
		
		log.debug("Inside Write method");
		
		for(TerminatedUsers tuser : terminatedUsers) {
			try {
				if( null != tuser) {
					//TerminatedUsers user = terminatedUsers.get(0);
					
					//Construct a map with username to pass path variables
					Map<String, String> pathVariables = new HashMap<String, String>();
					pathVariables.put("username", tuser.getUserName());
					
					//Construct headers
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
					
					//Construct request Entity
					HttpEntity<TerminatedUsers> requestEntity = new HttpEntity<TerminatedUsers>(tuser,headers);
					
					restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
					//Make REST API call 
					ResponseEntity<String> strResponse = restTemplate.exchange(URI_TO_UPDATE_USER_STATUS, HttpMethod.POST, requestEntity, String.class, pathVariables);
					
				}
			}catch(RestClientException rse) {
				log.error("Exception occured in write method - {}"+rse.getMessage());
			}
			
		}
		
		
		
	}
}
