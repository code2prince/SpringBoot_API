package com.example.LearingRestAPI.Repository;

import com.example.LearingRestAPI.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

//It took two params JpaRepository<Entity name, type of Id of entity>
@Repository //Optional hai
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.isActive = true")
    //@Procedure(procedureName = "SP_Student")
    List<Student> getAllActiveStudent();
}
