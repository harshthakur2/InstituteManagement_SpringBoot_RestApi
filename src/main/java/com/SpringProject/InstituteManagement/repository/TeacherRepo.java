package com.SpringProject.InstituteManagement.repository;

import com.SpringProject.InstituteManagement.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher , Long> {

}
