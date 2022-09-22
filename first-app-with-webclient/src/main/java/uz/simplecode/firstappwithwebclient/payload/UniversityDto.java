package uz.simplecode.firstappwithwebclient.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniversityDto {
    private Integer id;
    private String name;
    private String address;
}
