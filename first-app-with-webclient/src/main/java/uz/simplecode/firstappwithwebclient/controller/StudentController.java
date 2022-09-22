package uz.simplecode.firstappwithwebclient.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.simplecode.firstappwithwebclient.entity.Student;
import uz.simplecode.firstappwithwebclient.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "STUDENT CONTROLLER")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Student>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) throws Exception {
        return studentService.saveStudent(student);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editStudent(@PathVariable Integer id, @RequestBody Student student) {
        return studentService.editStudent(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) throws Exception {
        return studentService.deleteStudent(id);
    }
}
