package com.SpringProject.InstituteManagement.repository;

import com.SpringProject.InstituteManagement.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Students, Long> {

   public Students findByIdAndTeacherId(Long sid, Long id);

}
