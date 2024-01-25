package com.example.jasperreportdemo.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.jasperreportdemo.service.StudentService;

import net.sf.jasperreports.engine.JRException;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/generate/{format}")
	public String jasperReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return studentService.jasperReport(format);
	}
	
}
