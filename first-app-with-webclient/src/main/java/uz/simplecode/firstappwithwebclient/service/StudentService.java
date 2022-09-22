package uz.simplecode.firstappwithwebclient.service;

import org.springframework.http.ResponseEntity;
import uz.simplecode.firstappwithwebclient.entity.Student;

import java.util.List;

public interface StudentService {

    ResponseEntity<List<Student>> getAllStudents();

    ResponseEntity<Student> getStudentById(Integer id);

    ResponseEntity<String> saveStudent(Student student) throws Exception;

    ResponseEntity<String> editStudent(Integer id, Student student);

    ResponseEntity<String> deleteStudent(Integer id) throws Exception;

}
