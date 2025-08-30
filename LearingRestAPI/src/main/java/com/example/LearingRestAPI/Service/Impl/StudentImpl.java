package com.example.LearingRestAPI.Service.Impl;

import com.example.LearingRestAPI.Dto.AddStudentRequestDto;
import com.example.LearingRestAPI.Dto.StudentDto;
import com.example.LearingRestAPI.Entity.Student;
import com.example.LearingRestAPI.Repository.StudentRepository;
import com.example.LearingRestAPI.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students =studentRepository.findAll();
        return students
                .stream()
                .map(student -> new StudentDto(student.getId(), student.getName(), student.getEmail()))
                .toList();
    }

    @Override
    public StudentDto getStudentById(Long id){
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student Not found with id: "+id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent=modelMapper.map(addStudentRequestDto,Student.class);
        Student student=studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("No such id exists");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        // We can use if existsById also
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student Not found with id: "+id));
        modelMapper.map(addStudentRequestDto,student);
        student=studentRepository.save(student);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto partialUpdateStudent(Long id, Map<String, Object> updates) {
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student Not found with id: "+id));

        updates.forEach((field, value)->{
            switch (field) {
                case "name":student.setName((String) value);
                break;
                case "email":student.setEmail((String) value);
                break;
                default: throw  new IllegalArgumentException("Field is not supported");
            }
        });
        Student newStudent=studentRepository.save(student);
        return modelMapper.map(newStudent,StudentDto.class);
    }
}
