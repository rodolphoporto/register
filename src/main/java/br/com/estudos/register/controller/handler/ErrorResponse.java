package br.com.estudos.register.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Long timestamp;

    private HttpStatus status;

    private String message;

    private String path;

    private List<FieldMessage> errors;

}
