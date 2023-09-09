package dev.blackshark.api.persistance.entity;

import dev.blackshark.api.domain.dto.DatosRequestMedico;
import dev.blackshark.api.domain.dto.NewDatosDireccionDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DIreccion {

    @NotBlank
    private String calle;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String ciudad;

    public DIreccion(NewDatosDireccionDTO direccion) {
        this.calle = direccion.getCalle();
        this.numero = direccion.getNumero();
        this.complemento = direccion.getComplemento();
        this.ciudad = direccion.getCiudad();
    }

    public DIreccion(DatosRequestMedico direccion) {
        this.calle = direccion.getDireccion().getCalle();
        this.numero = direccion.getDireccion().getNumero();
        this.complemento = direccion.getDireccion().getComplemento();
        this.ciudad = direccion.getDireccion().getCiudad();
    }

}