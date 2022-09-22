package uz.simplecode.secondappwithwebclient.service;

import org.springframework.http.ResponseEntity;
import uz.simplecode.secondappwithwebclient.entity.University;
import uz.simplecode.secondappwithwebclient.payload.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UniversityService {

    ResponseEntity<ApiResponse<List<University>>> getAllUniversity(HttpServletRequest request);

    ResponseEntity<ApiResponse<University>> getUniversityById(Integer id);

    ResponseEntity<ApiResponse<String>> saveUniversity(University university) throws Exception;

    ResponseEntity<ApiResponse<String>> editUniversity(Integer id, University university) throws Exception;

    ResponseEntity<ApiResponse<String>> deleteUniversity(Integer id);

    ResponseEntity<?> downloadFile(HttpServletRequest request, HttpServletResponse response, Integer number) throws IOException;
}
