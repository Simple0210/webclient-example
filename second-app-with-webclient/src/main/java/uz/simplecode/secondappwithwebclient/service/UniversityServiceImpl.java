package uz.simplecode.secondappwithwebclient.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import uz.simplecode.secondappwithwebclient.entity.University;
import uz.simplecode.secondappwithwebclient.payload.ApiResponse;
import uz.simplecode.secondappwithwebclient.repository.UniversityRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public ResponseEntity<ApiResponse<List<University>>> getAllUniversity(HttpServletRequest request) {
        List<University> all = universityRepository.findAll();
        if (all.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse<>(false, all, "Universities not found!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>(true, all, "All Universities!"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ApiResponse<University>> getUniversityById(Integer id) {
        Optional<University> byId = universityRepository.findById(id);
        return byId.map(university ->
                new ResponseEntity<>(new ApiResponse<>(true, university, ""), HttpStatus.OK))
                .orElseGet(() ->
                        new ResponseEntity<>(new ApiResponse<>(false, null, "UNIVERSITY TOPILMADI!"), HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<ApiResponse<String>> saveUniversity(University university) throws Exception {
        try {
            universityRepository.save(university);
            return new ResponseEntity<>(new ApiResponse<>(true, "Universitet ma`lumotlari muvaffaqiyatli saqlandi!", ""), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Universitet ma`lumotlari saqlanmadi!");
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> editUniversity(Integer id, University university) throws Exception {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()) {
            University university1 = byId.get();
            university1.setName(university.getName());
            university1.setAddress(university.getAddress());
            try {
                universityRepository.save(university1);
                return new ResponseEntity<>(new ApiResponse<>(true, "Universitet ma`lumotlari muvaffaqiyatli o`zgartirildi!", ""), HttpStatus.CREATED);
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Universitet ma`lumotlari saqlanmadi!");
            }
        } else {
            return new ResponseEntity<>(new ApiResponse<>(false, "Universitet topilmadi!", ""), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<String>> deleteUniversity(Integer id) {
        try {
            universityRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse<>(true, id + " - University ma`lumotlari muvaffaqiyatli o`chirildi", ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(false, id + " - University not exists!", "Error: "), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> downloadFile(HttpServletRequest request, HttpServletResponse response, Integer number) throws IOException {
        byte[] bytes = new byte[0];
        if (number > 2) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            return new ResponseEntity<>(new ApiResponse<>(false, "Number xato!", "Error!"), HttpStatus.BAD_REQUEST);
        } else {
            File file;
            if (number == 1) {
                file = new File("D:\\DEMO-PROJECTS\\second-app-with-webclient\\src\\main\\resources\\STIR-516735461.pdf");
            } else {
                file = new File("D:\\DEMO-PROJECTS\\second-app-with-webclient\\src\\main\\resources\\API.txt");
            }

            if (file.exists()) {
                String mimeType = URLConnection.guessContentTypeFromName(file.getName());
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }
                response.setContentType(mimeType);

                response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));

                response.setContentLength((int) file.length());

                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                bytes = inputStream.readAllBytes();
                FileCopyUtils.copy(bytes, response.getOutputStream());

            }
            return null;

//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            return new ResponseEntity<>(new ApiResponse<>(true, bytes, "This is " + file.getName() + " file!"), HttpStatus.OK);
        }
    }
}