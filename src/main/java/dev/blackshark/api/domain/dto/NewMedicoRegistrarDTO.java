package dev.blackshark.api.domain.dto;

import dev.blackshark.api.persistance.entity.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor  @AllArgsConstructor
public class NewMedicoRegistrarDTO {
    @NotNull @NotBlank
    private String nombre;
    @NotNull @NotBlank  @Email
    private String email;
    @NotBlank @Pattern(regexp = "\\d{4,6}")
    private String dni;

    @NotNull @NotBlank
    private Especialidad especialidad;
    @NotNull
    private NewDatosDireccionDTO direccionDTO;
}
