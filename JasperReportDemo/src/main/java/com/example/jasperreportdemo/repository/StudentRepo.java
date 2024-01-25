package com.example.jasperreportdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jasperreportdemo.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{

}
