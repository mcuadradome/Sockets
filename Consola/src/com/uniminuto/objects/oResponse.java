package com.uniminuto.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class oResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> response;
	private ArrayList<String> errors;
	private String command;

	public ArrayList<String> getResponse() {
		return response;
	}

	public void setResponse(ArrayList<String> response) {
		this.response = response;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	
	
}
