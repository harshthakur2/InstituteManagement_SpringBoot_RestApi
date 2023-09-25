package com.SpringProject.InstituteManagement.service;

import com.SpringProject.InstituteManagement.entities.Students;
import com.SpringProject.InstituteManagement.entities.Teacher;
import com.SpringProject.InstituteManagement.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepo teacherRepo;

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher createTeacher(Teacher teacher) {
        if (teacher.getStudents() == null) {
            teacher.setStudents(new ArrayList<>()); // Initialize with an empty list if it's null
        }

        List<Students> studentList = teacher.getStudents();
        studentList.forEach(e -> e.setTeacher(teacher)); // For each student, set the current teacher

        teacher.setStudents(studentList); // Set the list of students for this teacher

        return teacherRepo.save(teacher);
    }

    public boolean deleteTeacherById(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepo.findById(id);

        if(optionalTeacher.isPresent()) {
            teacherRepo.deleteById(id);
            return true;
        }
        else{
            return false;
        }

    }

    public Teacher updateTeacher(Teacher teacher) {

        return teacherRepo.save(teacher);
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepo.findById(id);
    }

}
