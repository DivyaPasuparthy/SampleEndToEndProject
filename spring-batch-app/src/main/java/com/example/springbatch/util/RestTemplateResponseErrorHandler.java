package com.example.springbatch.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler{

	private static final Logger log = LoggerFactory.getLogger(RestTemplateResponseErrorHandler.class);
	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		return (
		          httpResponse.getStatusCode().series() == Series.CLIENT_ERROR 
		          || httpResponse.getStatusCode().series() == Series.SERVER_ERROR);
	}

	/*Only Errors are logged here and no new exceptions are thrown- just to keep the code simple*/
	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if (httpResponse.getStatusCode()
		          .series() == Series.SERVER_ERROR) {
		           log.error("Server Error Occured");
		        } else if (httpResponse.getStatusCode()
		          .series() == Series.CLIENT_ERROR) {
		        	 log.error("Client Error Occured");
		            if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				    	log.error("Record not found");
		            }
		        }
		
	}
	

}
