package uz.simplecode.firstappwithwebclient.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SecondAppResponse<T> implements Serializable {
    private Boolean success;
    private T data;
    private String message;
}
