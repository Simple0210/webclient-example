package uz.simplecode.firstappwithwebclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;
import uz.simplecode.firstappwithwebclient.mapper.UniversityDataMapper;
import uz.simplecode.firstappwithwebclient.payload.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
public class UniversityDataServiceImpl implements UniversityDataService {

    private static final String HEADER_NAME_FOR_SECURITY = "Authorization";
    private static final String HEADER_VALUE_FOR_SECURITY = "Basic " + Base64Utils.encodeToString(("first_app" + ":" + "FA123321").getBytes(UTF_8));


    private final WebClient UWC;


    public UniversityDataServiceImpl(WebClient webClient) {
        this.UWC = webClient.mutate().uriBuilderFactory(getFactory("http://localhost:8082/api/universities")).build();
    }

    public DefaultUriBuilderFactory getFactory(String baseUrl) {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        return factory;
    }


    public Mono<ApiResponse<List<UniversityDto>>> reqGetAllUniversity() {
        return UWC.get()
                .uri("/get")
                .header(HEADER_NAME_FOR_SECURITY, HEADER_VALUE_FOR_SECURITY)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<List<UniversityDto>>>() {
                });
    }


    public Mono<ApiResponse<UniversityDto>> reqGetUniversityById(Integer id) {
        return UWC.get()
                .uri("/get/{id}", id)
                .header(HEADER_NAME_FOR_SECURITY, HEADER_VALUE_FOR_SECURITY)
                .exchangeToMono(res -> {
                    if (res.statusCode().is4xxClientError()) {
                        res.body((httpRes, context) -> {
                            return httpRes.getBody();
                        });
                        return res.bodyToMono(new ParameterizedTypeReference<ApiResponse<UniversityDto>>() {
                        });
                    } else {
                        return res.bodyToMono(new ParameterizedTypeReference<ApiResponse<UniversityDto>>() {
                        });
                    }
                });
    }

    public Mono<ApiResponse<String>> reqSaveUniversity(UniversitySaveDto universitySaveDto) {
        return UWC.post()
                .uri("/add")
                .header(HEADER_NAME_FOR_SECURITY, HEADER_VALUE_FOR_SECURITY)
                .body(Mono.just(universitySaveDto), UniversitySaveDto.class)
                .exchangeToMono(res -> {
                    if (res.statusCode().isError()) {
                        res.body((httpRes, context) -> {
                            return httpRes.getBody();
                        });
                        return res.bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {
                        });
                    } else {
                        return res.bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {
                        });
                    }
                });
    }

    public Mono<ApiResponse<String>> reqEditUniversity(UniversitySaveDto universitySaveDto, Integer id) {
        return UWC.put()
                .uri("/edit/{id}", id)
                .header(HEADER_NAME_FOR_SECURITY, HEADER_VALUE_FOR_SECURITY)
                .body(Mono.just(universitySaveDto), UniversitySaveDto.class)
                .exchangeToMono(res -> {
                    if (res.statusCode().isError()) {
                        res.body((httpRes, context) -> {
                            return httpRes.getBody();
                        });
                        return res.bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {
                        });
                    }
                    return res.bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {
                    });
                });

    }

    public Mono<ApiResponse<String>> reqDeleteUniversity(Integer id) {
        return UWC.delete()
                .uri("/delete/{id}", id)
                .header(HEADER_NAME_FOR_SECURITY, HEADER_VALUE_FOR_SECURITY)
                .exchangeToMono(res -> {
                    if (res.statusCode().isError()) {
                        res.body((httpRes, context) -> {
                            return httpRes.getBody();
                        });
                        return res.bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {
                        });
                    } else {
                        return res.bodyToMono(new ParameterizedTypeReference<ApiResponse<String>>() {
                        });
                    }
                });
    }

    public Mono<SecondAppResponse<List<UniversityDto>>> reqGetAllUniversityWithSecondAppResponse() {
        return UWC.get()
                .uri("/get")
                .header(HEADER_NAME_FOR_SECURITY, HEADER_VALUE_FOR_SECURITY)
                .exchangeToMono(res -> {
                    if (res.statusCode().isError()) {
                        res.body((httpRes, context) -> {
                            return httpRes.getBody();
                        });
                        return res.bodyToMono(new ParameterizedTypeReference<SecondAppResponse<List<UniversityDto>>>() {
                        });
                    } else {
                        return res.bodyToMono(new ParameterizedTypeReference<SecondAppResponse<List<UniversityDto>>>() {
                        });
                    }
                });
    }

    public Mono<?> reqDownload(Integer number) {
        return UWC.get()
                .uri("/download-file/{number}", number)
                .header(HEADER_NAME_FOR_SECURITY, HEADER_VALUE_FOR_SECURITY)
                .accept(MediaType.ALL)
                .exchangeToMono(res -> {
                    if (res.statusCode().is4xxClientError()) {
                        res.body((httpRes, context) -> {
                            return httpRes.getBody();
                        });
                        return res.bodyToMono(new ParameterizedTypeReference<>() {
                        }).doOnSuccess(c -> {
                            res.bodyToMono(new ParameterizedTypeReference<CustomApiResponse>() {
                            });
                        });
                    } else {
                        return res.bodyToMono(ByteArrayResource.class).map(ByteArrayResource::getByteArray);
                    }
                });
    }


    @Override
    public Mono<ResponseEntity<?>> download(Integer number) {
        return this.reqDownload(number).map(res -> {
            return new ResponseEntity<>(res, HttpStatus.OK);
        });
    }

    @Override
    public Mono<ResponseEntity<ApiResponse<List<UniversityDto>>>> getAllUniversity() {
        return this.reqGetAllUniversity().map(res -> {
            if (res.getSuccess()) {
                return new ResponseEntity<>(new ApiResponse<>(true, res.getData(), "Second appdan kelgan data"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, new ArrayList<>(), "External error: " + res.getMessage()), HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public Mono<ResponseEntity<ApiResponse<UniversityDto>>> getUniversityById(Integer id) {
        return this.reqGetUniversityById(id).map(res -> {
            if (res.getSuccess()) {
                return new ResponseEntity<>(new ApiResponse<>(true, res.getData(), res.getMessage()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, res.getData(), res.getMessage()), HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public Mono<ResponseEntity<ApiResponse<String>>> saveUniversity(UniversitySaveDto universitySaveDto) {
        return this.reqSaveUniversity(universitySaveDto).map(res -> {
            if (res.getSuccess()) {
                return new ResponseEntity<>(new ApiResponse<>(true, res.getData(), res.getMessage()), HttpStatus.CREATED);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, res.getData(), res.getMessage()), HttpStatus.BAD_REQUEST);
        });
    }

    @Override
    public Mono<ResponseEntity<ApiResponse<String>>> editUniversity(UniversitySaveDto universitySaveDto, Integer id) {
        return this.reqEditUniversity(universitySaveDto, id).map(res -> {
            if (res.getSuccess()) {
                return new ResponseEntity<>(new ApiResponse<>(true, res.getData(), res.getMessage()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, res.getData(), res.getMessage()), HttpStatus.BAD_REQUEST);
        });
    }

    @Override
    public Mono<ResponseEntity<ApiResponse<String>>> deleteUniversity(Integer id) {
        return this.reqDeleteUniversity(id).map(res -> {
            if (res.getSuccess()) {
                return new ResponseEntity<>(new ApiResponse<>(true, res.getData(), res.getMessage()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, res.getData(), res.getMessage()), HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public Mono<ResponseEntity<ApiResponse<List<UniversityDto>>>> getAllUniversityWithSecondAppResponse() {
        return this.reqGetAllUniversityWithSecondAppResponse().map(res -> {
            if (res.getSuccess()) {
                return new ResponseEntity<>(new ApiResponse<>(true, res.getData().stream().map(UniversityDataMapper::parseToDto).collect(Collectors.toList()), res.getMessage()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, res.getData(), res.getMessage()), HttpStatus.NOT_FOUND);
        });
    }
}
