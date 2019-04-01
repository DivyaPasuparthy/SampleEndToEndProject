package com.example.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.springbatch.model.TerminatedUsers;

@Component
public class TerminatedUsersItemProcessor implements ItemProcessor<TerminatedUsers,TerminatedUsers>{

	private static final Logger log = LoggerFactory.getLogger(TerminatedUsersItemProcessor.class);

	/* This method can have the processing logic. 
	 * For the current use case, no processing is required.
	 * */
	@Override
	public TerminatedUsers process(final TerminatedUsers terminatedUsers) throws Exception {
		log.debug("Inside Process method");
		return terminatedUsers;
	}
	
}
