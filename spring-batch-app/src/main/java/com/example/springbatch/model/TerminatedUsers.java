package com.example.springbatch.model;

import org.springframework.stereotype.Component;

@Component
public class TerminatedUsers {

	private String userName;
	private String terminationDate;
	
	public TerminatedUsers() {
		
	}
	
	public TerminatedUsers( String userName, String terminationDate) {
		this.userName = userName;
		this.terminationDate = terminationDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public String getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}
	
	@Override
    public String toString() {
        return "userName: " + userName + ", terminationDate: " + terminationDate;
    }
}
