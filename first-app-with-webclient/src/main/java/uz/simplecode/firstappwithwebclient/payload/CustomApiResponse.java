package uz.simplecode.firstappwithwebclient.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomApiResponse {

    private Boolean success;
    private String data;
    private String message;
}
