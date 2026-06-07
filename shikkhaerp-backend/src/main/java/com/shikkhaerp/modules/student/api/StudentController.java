package com.shikkhaerp.modules.student.api;

import com.shikkhaerp.core.dto.ApiResponse;
import com.shikkhaerp.modules.student.entity.Student;
import com.shikkhaerp.modules.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ApiResponse<Student> create(@RequestBody Student student) {
        return ApiResponse.success(studentService.create(student), "Student created successfully!");
    }

    @GetMapping
    public ApiResponse<List<Student>> getAll() {
        return ApiResponse.success(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Student> getById(@PathVariable String id) {
        return ApiResponse.success(studentService.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<Student> update(@PathVariable String id, @RequestBody Student student) {
        return ApiResponse.success(studentService.update(id, student));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable String id) {
        studentService.delete(id);
        return ApiResponse.success(null, "Student deleted successfully!");
    }
}