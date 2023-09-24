package com.SpringProject.InstituteManagement.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column(name="id",nullable = false)
    private Long id;

    private String name ;

    private String subject;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
    private List<Students> students;

    public Teacher()
    {

    }

    public Teacher(Long id, String name, String subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" + "id=" + id + ", name='" + name + '\'' + ", subject='" + subject + '\'' + '}';
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = students;
    }
}
