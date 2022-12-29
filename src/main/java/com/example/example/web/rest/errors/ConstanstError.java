package com.example.example.web.rest.errors;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter @RequiredArgsConstructor @NoArgsConstructor
public class ConstanstError {


    @NonNull
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy hh:mm:ss")
    private LocalDateTime dateTime=LocalDateTime.now();
    @NonNull
    private String message;
}
