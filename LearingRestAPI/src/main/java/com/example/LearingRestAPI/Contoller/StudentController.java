package com.example.LearingRestAPI.Contoller;

import com.example.LearingRestAPI.Dto.AddStudentRequestDto;
import com.example.LearingRestAPI.Dto.StudentDto;
import com.example.LearingRestAPI.Service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
@Tag(name = "Student API", description = "CRUD operations for students")

public class StudentController {
    @GetMapping("/getStudent")
    public StudentDto getStudent(){
        return new StudentDto(4L,"prince","code@gmail.com");
    }

    //----------------studentList------------------------
    // ---------Earlier controller Connecting with Repository but not good practice need to connect with service layer---------
    //    private StudentRepository studentRepository;
    //    public StudentController(StudentRepository studentRepository) {
    //        this.studentRepository = studentRepository;
    //    }

    //    @GetMapping("/studentList")
    //    public List<Student> getAllStudent(){
    //        return studentRepository.findAll();
    //    }

    private final StudentService studentService;

    @GetMapping("/getStudentList")
    @Operation(summary = "Get all students", description = "Fetches a list of all students")
    public ResponseEntity<List<StudentDto>>getAllStudentList(){
        //return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
       return ResponseEntity.ok(studentService.getAllStudents());  //  ----Short hand for Ok 200
    }

    //-----------------------------------------------------------

    @GetMapping("/getStudent/{id}")
    public StudentDto getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/createStudent")
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(studentService.updateStudent(id,addStudentRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> partialUpdateStudent(@PathVariable Long id, @RequestBody Map<String ,Object> updates){
        return ResponseEntity.ok(studentService.partialUpdateStudent(id, updates));
    }

}
