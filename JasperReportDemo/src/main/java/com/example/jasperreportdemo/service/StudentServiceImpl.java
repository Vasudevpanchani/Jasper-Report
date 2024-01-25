package com.example.jasperreportdemo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.jasperreportdemo.model.Student;
import com.example.jasperreportdemo.repository.StudentRepo;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public String jasperReport(String format) throws FileNotFoundException, JRException {
		String path="C:\\Users\\vasudevpanchani\\Downloads";
		List<Student> students = studentRepo.findAll();
		File file=ResourceUtils.getFile("classpath:student.jrxml");
		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(students);
		Map<String, Object> parameter=new HashMap<>();
		parameter.put("Created By", "Vasudev Panchani");
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport, parameter,dataSource);
		if(format.equalsIgnoreCase("html"))
		{
			JasperExportManager.exportReportToHtmlFile(jasperPrint,path + "\\student.html");
			return "report generated in path :" + path + "\\student.html";
		}else if(format.equalsIgnoreCase("pdf"))
		{
			JasperExportManager.exportReportToPdfFile(jasperPrint,path + "\\student.pdf");
			return "report generated in path :" + path + "\\student.pdf";
		}else {
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput( new SimpleOutputStreamExporterOutput(path+"\\student.xls"));
			exporter.exportReport();
			return "report generated in path :" + path + "\\student.xls";
		}
	}

}
