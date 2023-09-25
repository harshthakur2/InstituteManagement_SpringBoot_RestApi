package com.SpringProject.InstituteManagement.controller;

import com.SpringProject.InstituteManagement.entities.Students;
import com.SpringProject.InstituteManagement.entities.Teacher;
import com.SpringProject.InstituteManagement.repository.StudentRepo;
import com.SpringProject.InstituteManagement.repository.TeacherRepo;
import com.SpringProject.InstituteManagement.service.StudentService;
import com.SpringProject.InstituteManagement.service.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teacher")
public class InstituteController
{
    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherRepo teacherRepo;
    @Autowired
    StudentRepo studentRepo;

    @GetMapping
    public List<Teacher> get(){
        return teacherRepo.findAll();
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //adding a new Teacher
    @PostMapping("/addTeacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
        Teacher addTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.ok(addTeacher);
        }

    //deleting a teacher

    @DeleteMapping("/deleteTeacher/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable long id){
        boolean deleteTeacher = teacherService.deleteTeacherById(id);
        if(deleteTeacher)
                return ResponseEntity.ok("Teacher is deleted");
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found.");

    }

    //updating a Teacher
    @PutMapping
    public Teacher update(@RequestBody @NotNull Teacher teacher) {
        if (teacher.getStudents() == null) {
            teacher.setStudents(new ArrayList<>()); // Initialize with an empty list if it's null
        }

        teacher.getStudents().forEach(e -> e.setTeacher(teacher));

        return teacherRepo.save(teacher);
    }

    //getting Teachers by id
    @GetMapping("/{id}")
    public Teacher getById(@PathVariable Long id ) {
        Optional<Teacher> findById = teacherRepo.findById(id);
        if(findById.isPresent())
            return findById.get();
        return null;
    }

    //getting list of students by teacher id
    @GetMapping("/{id}/students")
    public List<Students> getAllStudents(@PathVariable("id") Long id){
        return teacherRepo.findById(id).get().getStudents();

    }

    //adding students in a teacher using teacher id
    @PostMapping("/{id}/student")
    public Students addStudents(@PathVariable("id")Long id,@RequestBody @NotNull Students student) {
        Teacher teacher =teacherRepo.findById(id).get();
        student.setTeacher(teacher);
        return studentRepo.save(student);
    }

    // getting Particular Student by Teacher id (teacher id / students / student ID)
    @GetMapping("/{id}/student/{sid}")
    public Students getById(@PathVariable("id")Long id,@PathVariable("sid")Long sid) {
        return studentRepo.findByIdAndTeacherId(sid, id);
    }

    //delete student by using teacher id(teacher id / student / student id)
    @DeleteMapping("/{id}/student/{sid}")
    public String deleteById(@PathVariable("id")Long id,@PathVariable("sid")Long sid) {
        Students std = studentRepo.findByIdAndTeacherId(sid, id);
        if( std!=null) {
            studentRepo.deleteById(sid);
            return "deleted";
        }
        return "could not delete";
    }

    //update student by id
    @PutMapping("/{id}/student")
    public Students updateEmp(@PathVariable("id")Long id,@RequestBody @NotNull Students std) {
        Students findByIdAndTeacherId = studentRepo.findByIdAndTeacherId(std.getId(), id);
        if(findByIdAndTeacherId!=null){
            std.setTeacher(teacherRepo.findById(id).get());
            return studentRepo.save(std);
        }
        return null;
    }

}
