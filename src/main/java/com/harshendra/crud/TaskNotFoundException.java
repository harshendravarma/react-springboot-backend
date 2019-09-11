package com.harshendra.crud;


@SuppressWarnings("serial")
public class TaskNotFoundException extends RuntimeException {
	
	public TaskNotFoundException(String exception) {
	    super(exception);
	  }

	}