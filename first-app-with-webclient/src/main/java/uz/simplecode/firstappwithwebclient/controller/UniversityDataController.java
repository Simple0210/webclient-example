package uz.simplecode.firstappwithwebclient.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import uz.simplecode.firstappwithwebclient.payload.ApiResponse;
import uz.simplecode.firstappwithwebclient.payload.UniversityDto;
import uz.simplecode.firstappwithwebclient.payload.UniversitySaveDto;
import uz.simplecode.firstappwithwebclient.service.UniversityDataService;

import java.util.List;

@RestController
@RequestMapping("/api/students/universities-data")
public class UniversityDataController {

    private final UniversityDataService universityDataService;

    public UniversityDataController(UniversityDataService universityDataService) {
        this.universityDataService = universityDataService;
    }

    @GetMapping("/get-all")
    public Mono<ResponseEntity<ApiResponse<List<UniversityDto>>>> getAllUniversityData() {
        return universityDataService.getAllUniversity();
    }

    @GetMapping("/get/{id}")
    public Mono<ResponseEntity<ApiResponse<UniversityDto>>> getUniversityById(@PathVariable Integer id) throws Exception {
        return universityDataService.getUniversityById(id);
    }

    @PostMapping("/add")
    public Mono<ResponseEntity<ApiResponse<String>>> saveUniversity(@RequestBody UniversitySaveDto universitySaveDto) {
        return universityDataService.saveUniversity(universitySaveDto);
    }

    @PutMapping("/edit{id}")
    public Mono<ResponseEntity<ApiResponse<String>>> editUniversity(@RequestBody UniversitySaveDto universitySaveDto, @PathVariable Integer id) {
        return universityDataService.editUniversity(universitySaveDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<ApiResponse<String>>> deleteUniversity(@PathVariable Integer id) {
        return universityDataService.deleteUniversity(id);
    }

    @GetMapping("/get-all-with-second-app-response")
    public Mono<ResponseEntity<ApiResponse<List<UniversityDto>>>> getAllUniversityWithSecondAppResponse() {
        return universityDataService.getAllUniversityWithSecondAppResponse();
    }

    @GetMapping(value = "/download-file/{number}")
    public Mono<ResponseEntity<?>> downloadFile(@PathVariable Integer number) {
        return universityDataService.download(number);
    }


}
