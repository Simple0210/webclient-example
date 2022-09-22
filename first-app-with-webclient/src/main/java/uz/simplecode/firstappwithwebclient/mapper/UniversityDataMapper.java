package uz.simplecode.firstappwithwebclient.mapper;

import uz.simplecode.firstappwithwebclient.payload.UniversityDto;

public class UniversityDataMapper {

    public static UniversityDto parseToDto(UniversityDto universityDto) {
        return UniversityDto.builder()
                .id(universityDto.getId())
                .name(universityDto.getName())
                .build();
    }
}
