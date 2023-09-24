package com.SpringProject.InstituteManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column(name="id",nullable = false)
    private Long id;
    private String Name;
    private Long contact;

    @ManyToOne
    @JsonIgnore
     Teacher teacher;

    public Students() {
    }

    public Students(Long id, String name, Long contact) {
        this.id = id;
        Name = name;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    @Override
    public String toString()
    {
        return "Students{" + "id=" + id + ", Name='" + Name + '\'' + ", contact=" + contact + '}';
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


}
