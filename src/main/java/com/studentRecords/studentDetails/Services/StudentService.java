package com.studentRecords.studentDetails.Services;

import com.studentRecords.studentDetails.dto.Studentdto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface StudentService {

    ResponseEntity<Object> saveStudent(Studentdto studentDto);

    List<Studentdto> getAllStudents();

    ResponseEntity<Object> updateStudent(Studentdto studentDto);

    Map<String, Object> getAllStudentsPagination(Integer startIndex, Integer maxlimit);

}
