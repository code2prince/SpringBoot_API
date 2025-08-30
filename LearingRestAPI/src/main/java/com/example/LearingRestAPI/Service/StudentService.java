package com.example.LearingRestAPI.Service;

import com.example.LearingRestAPI.Dto.AddStudentRequestDto;
import com.example.LearingRestAPI.Dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudent(Long id);

    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

    StudentDto partialUpdateStudent(Long id, Map<String, Object> updates);
}
