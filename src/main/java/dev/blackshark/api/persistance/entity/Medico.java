package dev.blackshark.api.persistance.entity;

import dev.blackshark.api.domain.dto.DatosRequestMedico;
import dev.blackshark.api.domain.dto.NewMedicoRegistrarDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor  @AllArgsConstructor
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Email
    @NotBlank   @NonNull
    private String email;

    @NonNull    @NotBlank
    @Column(length = 9, unique = true)
    private String dni;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private DIreccion direccion;


    public Medico(NewMedicoRegistrarDTO medicoRegistrarDTO) {
        this.nombre = medicoRegistrarDTO.getNombre();
        this.email = medicoRegistrarDTO.getEmail();
        this.dni = medicoRegistrarDTO.getDni();
        this.especialidad = medicoRegistrarDTO.getEspecialidad();
        this.direccion = new DIreccion(medicoRegistrarDTO.getDireccionDTO());
    }

    public Medico(DatosRequestMedico datosRequestMedico) {
        this.nombre = datosRequestMedico.getNombre();
        this.email = datosRequestMedico.getEmail();
        this.dni = datosRequestMedico.getDni();
        this.especialidad = datosRequestMedico.getEspecialidad();
        this.direccion = new DIreccion(datosRequestMedico.getDireccion());
    }
}