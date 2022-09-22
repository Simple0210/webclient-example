package uz.simplecode.firstappwithwebclient.service;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import uz.simplecode.firstappwithwebclient.payload.ApiResponse;
import uz.simplecode.firstappwithwebclient.payload.UniversityDto;
import uz.simplecode.firstappwithwebclient.payload.UniversitySaveDto;

import java.util.List;

public interface UniversityDataService {


    Mono<ResponseEntity<ApiResponse<List<UniversityDto>>>> getAllUniversity();

    Mono<ResponseEntity<ApiResponse<UniversityDto>>> getUniversityById(Integer id);

    Mono<ResponseEntity<ApiResponse<String>>> saveUniversity(UniversitySaveDto universitySaveDto);

    Mono<ResponseEntity<ApiResponse<String>>> editUniversity(UniversitySaveDto universitySaveDto, Integer id);

    Mono<ResponseEntity<ApiResponse<String>>> deleteUniversity(Integer id);

    Mono<ResponseEntity<ApiResponse<List<UniversityDto>>>> getAllUniversityWithSecondAppResponse();

    Mono<ResponseEntity<?>> download(Integer number);
}
