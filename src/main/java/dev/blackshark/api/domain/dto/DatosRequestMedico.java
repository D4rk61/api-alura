package dev.blackshark.api.domain.dto;

import dev.blackshark.api.persistance.entity.DIreccion;
import dev.blackshark.api.persistance.entity.Especialidad;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor  @AllArgsConstructor
public class DatosRequestMedico {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Email(message = "El correo electrónico debe ser válido")
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    private String telefono;

    @NotBlank(message = "El DNI no puede estar vacío")
    private String dni;

    @NotNull(message = "La especialidad no puede ser nula")
    private Especialidad especialidad;

    @Valid // Para habilitar la validación de la dirección
    private NewDatosDireccionDTO direccion;
}
