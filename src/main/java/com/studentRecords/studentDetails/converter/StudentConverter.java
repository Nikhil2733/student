package com.studentRecords.studentDetails.converter;

import com.studentRecords.studentDetails.Entity.Student;
import com.studentRecords.studentDetails.dto.Studentdto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentConverter {

    public static Student dtoToEntity(Studentdto studentDto){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setAddress(studentDto.getAddress());
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            Date date = (Date)formatter.parse(studentDto.getBirthDate());
            student.setBirthDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        student.setName(studentDto.getName());
        student.setRegNo(studentDto.getRegNo());
        return student;
    }

    public static Studentdto entityToDto(Student student) {
        Studentdto studentDto = new Studentdto();
        studentDto.setId(student.getId());
        studentDto.setAddress(student.getAddress());
        studentDto.setBirthDate(student.getBirthDate().toString());
        studentDto.setName(student.getName());
        studentDto.setRegNo(student.getRegNo());
        return studentDto;
    }
}
