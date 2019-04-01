package com.example.springbatch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import com.example.springbatch.model.TerminatedUsers;

@Configuration
public class BeanConfiguration {
	
	@Bean
	@Qualifier("restTemplate")
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
	
	/**
	 * This method reads data(username and terminationDate)
	 * from TerminateionFile.csv file and constructs TerminatedUsers.
	 * @return
	 */
	@Bean
	@Qualifier("reader")
	public FlatFileItemReader<TerminatedUsers> reader() {
		return new FlatFileItemReaderBuilder<TerminatedUsers>().name("terminatedUsersItemReader")
				.resource(new ClassPathResource("TerminationFile.csv")).delimited()
				.names(new String[] { "userName", "terminationDate" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<TerminatedUsers>() {
					{
						setTargetType(TerminatedUsers.class);
					}
				}).build();
	}	
}
