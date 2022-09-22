package uz.simplecode.secondappwithwebclient.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.simplecode.secondappwithwebclient.entity.University;
import uz.simplecode.secondappwithwebclient.payload.ApiResponse;
import uz.simplecode.secondappwithwebclient.service.UniversityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/universities")
@Tag(name = "UNIVERSITY CONTROLLER")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<University>>> getAllUniversity(HttpServletRequest request) {
        return universityService.getAllUniversity(request);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<University>> getUniversityById(@PathVariable Integer id) {
        return universityService.getUniversityById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> saveUniversity(@RequestBody University university) throws Exception {
        return universityService.saveUniversity(university);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ApiResponse<String>> editUniversity(@PathVariable Integer id, @RequestBody University university) throws Exception {
        return universityService.editUniversity(id, university);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUniversity(@PathVariable Integer id) {
        return universityService.deleteUniversity(id);
    }

    @GetMapping(value = "/download-file/{number}")
    public ResponseEntity<?> downloadFile(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer number) throws IOException {
        return universityService.downloadFile(request, response, number);
    }
}
