package com.SpringProject.InstituteManagement.service;

import com.SpringProject.InstituteManagement.entities.Students;
import com.SpringProject.InstituteManagement.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public Students createStudent(Students student) {

        return studentRepo.save(student);
    }

    public void deleteStudentById(Long id) {

        studentRepo.deleteById(id);
    }

    public Students updateStudent(Students student) {

        return studentRepo.save(student);
    }

    public Optional<Students> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    public Students getStudentByIdAndTeacherId(Long studentId, Long teacherId) {

        return studentRepo.findByIdAndTeacherId(studentId, teacherId);
    }
}
