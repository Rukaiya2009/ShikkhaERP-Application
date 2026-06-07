package com.shikkhaerp.modules.student.service;

import com.shikkhaerp.modules.student.entity.Student;
import com.shikkhaerp.modules.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(String id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found!"));
    }

    public Student update(String id, Student student) {
        Student existing = getById(id);
        existing.setName(student.getName());
        existing.setClassName(student.getClassName());
        existing.setPhone(student.getPhone());
        existing.setAddress(student.getAddress());
        return studentRepository.save(existing);
    }

    public void delete(String id) {
        Student student = getById(id);
        student.setActive(false);
        studentRepository.save(student);
    }
}