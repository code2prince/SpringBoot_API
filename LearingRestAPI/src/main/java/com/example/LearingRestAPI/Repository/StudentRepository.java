package com.example.LearingRestAPI.Repository;

import com.example.LearingRestAPI.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//It took two params JpaRepository<Entity name, type of Id of entity>
@Repository //Optional hai
public interface StudentRepository extends JpaRepository<Student, Long> {
}
