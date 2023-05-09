package br.com.estudos.register.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    @Size(min = 3, max = 10, message = "O tamanho deve ser entre 3 e 10")
    private String name;

    @NotBlank
    @Size(min = 4, max = 6, message = "O tamanho deve ser entre 4 e 6")
    private String password;

    @NotNull(message = "O campo de data não pode ser nulo")
    private java.time.LocalDate dateOfBirth;

}
