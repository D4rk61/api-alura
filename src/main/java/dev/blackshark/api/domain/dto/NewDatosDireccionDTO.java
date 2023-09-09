package dev.blackshark.api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor  @AllArgsConstructor
public class NewDatosDireccionDTO {
    @NotBlank
    private String calle;
    @NotBlank
    private String distrito;
    @NotBlank
    private String ciudad;
    @NotBlank
    private String numero;
    @NotBlank
    private String complemento;
}
