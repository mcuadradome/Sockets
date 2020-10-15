package com.uniminuto.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class OLinesFile implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> detailsFile;
	private Map<String, ArrayList<String>> LinesFile;
	private String path;
	private String bytes;
	
	public ArrayList<String> getDetailsFile() {
		return detailsFile;
	}
	public void setDetailsFile(ArrayList<String> detailsFile) {
		this.detailsFile = detailsFile;
	}
	public Map<String, ArrayList<String>> getLinesFile() {
		return LinesFile;
	}
	public void setLinesFile(Map<String, ArrayList<String>> linesFile) {
		LinesFile = linesFile;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBytes() {
		return bytes;
	}
	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	
	
	
	
}
