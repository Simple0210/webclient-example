package uz.simplecode.firstappwithwebclient.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.simplecode.firstappwithwebclient.entity.Student;
import uz.simplecode.firstappwithwebclient.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Student> getStudentById(Integer id) {
        Optional<Student> byId = studentRepository.findById(id);
        return byId.map(
                student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<String> saveStudent(Student student) throws Exception {
        try {
            studentRepository.save(student);
            return new ResponseEntity<>("Student ma`lumotlari muvaffaqiyatli saqlandi!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Student ma`lumotlari saqlanmadi!");
        }
    }

    @Override
    public ResponseEntity<String> editStudent(Integer id, Student student) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student student1 = optional.get();
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setPhoneNumber(student.getPhoneNumber());
            studentRepository.save(student1);
            return new ResponseEntity<>("Student ma`lumotlari muvaffaqiyatli o`zgartirildi!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Student topilmadi!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> deleteStudent(Integer id) throws Exception {

        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>("Student ma`lumotlari muvaffaqiyatli o`chirildi!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Student ma`lumotlari o`chirilmadi!");
        }
    }
}
