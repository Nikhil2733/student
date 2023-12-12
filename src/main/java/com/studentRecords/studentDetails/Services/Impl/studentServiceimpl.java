package com.studentRecords.studentDetails.Services.Impl;

import com.studentRecords.studentDetails.Entity.Student;
import com.studentRecords.studentDetails.Rpository.CoustomizedRepository;
import com.studentRecords.studentDetails.Rpository.StudentRepository;
import com.studentRecords.studentDetails.Services.StudentService;
import com.studentRecords.studentDetails.converter.StudentConverter;
import com.studentRecords.studentDetails.dto.Studentdto;
import jakarta.persistence.RollbackException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class studentServiceimpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CoustomizedRepository customStudentRepository;

    @Override
    public ResponseEntity<Object> saveStudent(Studentdto studentDto) {

        Student student = null;
        try {
            student = studentRepository.save(StudentConverter.dtoToEntity(studentDto));
        }catch(RollbackException e) {
            return ResponseEntity.badRequest().body(e);
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(student.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public List<Studentdto> getAllStudents() {
        List<Studentdto> studentDtoList = new ArrayList<Studentdto>();
        List<Student> studentList = studentRepository.findAll();
        for (Student student : studentList) {
            Studentdto studentDto = StudentConverter.entityToDto(student);
            studentDtoList.add(studentDto);
        }
        return studentDtoList;
    }

    @Override
    public ResponseEntity<Object> updateStudent(Studentdto studentDto) {
		/*Optional<Student> studentOptional = studentRepository.findById(studentDto.getId());
		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();*/
        Student student = null;
        try {
            student = studentRepository.getOne(studentDto.getId());
        }catch(RollbackException e) {
            return ResponseEntity.badRequest().body(e);
        }
        if(student == null) {
            return ResponseEntity.notFound().build();
        }

        studentRepository.save(StudentConverter.dtoToEntity(studentDto));
        return ResponseEntity.noContent().build();

    }

    @Override
    public Map<String, Object> getAllStudentsPagination(Integer startIndex, Integer maxlimit) {
        List<Studentdto> studentDtoList = new ArrayList<Studentdto>();
        Map<String, Object> modelMap = new HashMap<String,Object>();
        List<Object[]> studentList = customStudentRepository.findPagedResultForStudents(startIndex, maxlimit);
        for (Object[] studentRow : studentList) {
            Studentdto studentDto = new Studentdto();
            studentDto.setId((Long)studentRow[0]);
            studentDto.setName((String)studentRow[1]);
            studentDto.setBirthDate(studentRow[2].toString());
            studentDto.setAddress((String)studentRow[3]);
            studentDto.setRegNo((Long)studentRow[4]);
            studentDtoList.add(studentDto);
        }
        modelMap.put("total", studentRepository.count());
        modelMap.put("students", studentDtoList);
        return modelMap;
    }
}
