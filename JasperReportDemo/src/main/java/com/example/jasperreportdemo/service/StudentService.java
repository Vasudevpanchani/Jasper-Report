package com.example.jasperreportdemo.service;

import java.io.FileNotFoundException;

import net.sf.jasperreports.engine.JRException;

public interface StudentService {
	
	public String jasperReport(String format) throws FileNotFoundException, JRException;

}
