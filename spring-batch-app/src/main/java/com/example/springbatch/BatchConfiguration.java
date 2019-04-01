package com.example.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springbatch.model.TerminatedUsers;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration{

	private static final Logger log = LoggerFactory.getLogger(BatchConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("reader")
	public ItemReader<TerminatedUsers> reader;
	
	@Autowired
	public TerminatedUsersItemProcessor processor;
	
	@Autowired
	public ItemWriter<TerminatedUsers> writer;
	
	@Bean
	public Job updateTerminatedUsers() {
		log.debug("Inside updateTerminatedUsers method");
		return jobBuilderFactory
				.get("updateTerminatedUsers")
				.incrementer(new RunIdIncrementer())
				.flow(readAndUpdateUsersStatus()).end().build();
	}

	@Bean
	public Step readAndUpdateUsersStatus() {
		log.debug("Inside readAndUpdateUsersStatus step");
		return stepBuilderFactory.
				get("readAndUpdateUsersStatus")
				.<TerminatedUsers, TerminatedUsers>chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}	
}
