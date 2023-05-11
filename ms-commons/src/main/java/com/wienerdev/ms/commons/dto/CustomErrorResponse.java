package com.wienerdev.ms.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}
