package com.studentRecords.studentDetails.controllers;

import com.studentRecords.studentDetails.Services.StudentService;
import com.studentRecords.studentDetails.dto.Studentdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentResource {

    @Autowired
    StudentService studentService;

    @RequestMapping(value="/students", method= RequestMethod.GET,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Studentdto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(value="/students/paging", method=RequestMethod.GET,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getAllStudentsPagination(@RequestParam("start") Integer startIndex,
                                                        @RequestParam("limit") Integer maxlimit) {
        return studentService.getAllStudentsPagination(startIndex,maxlimit);
    }

    @RequestMapping(value="/students", method=RequestMethod.POST,
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveStudent(@Validated @RequestBody Studentdto studentDto) {
        return studentService.saveStudent(studentDto);
    }

    @RequestMapping(value="/students", method=RequestMethod.PUT,
            consumes=MediaType.APPLICATION_JSON_VALUE,
            produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateStudent(@Validated @RequestBody Studentdto studentDto) {
        return studentService.updateStudent(studentDto);
    }
}
