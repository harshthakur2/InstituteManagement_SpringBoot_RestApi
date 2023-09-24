package com.SpringProject.InstituteManagement.service;

import com.SpringProject.InstituteManagement.entities.Students;
import com.SpringProject.InstituteManagement.entities.Teacher;
import com.SpringProject.InstituteManagement.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return teacherRepo.save(teacher);
    }

    public void deleteTeacherById(Long id) {

        teacherRepo.deleteById(id);
    }

    public Teacher updateTeacher(Teacher teacher) {

        return teacherRepo.save(teacher);
    }

    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepo.findById(id);
    }

}
