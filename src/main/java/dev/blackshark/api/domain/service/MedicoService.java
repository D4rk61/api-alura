package dev.blackshark.api.domain.service;

import dev.blackshark.api.domain.dto.DatosRequestMedico;
import dev.blackshark.api.domain.dto.NewMedicoRegistrarDTO;
import dev.blackshark.api.domain.dto.MostrarMedioDTO;
import dev.blackshark.api.persistance.entity.Medico;
import dev.blackshark.api.persistance.repository.crud.IMedicoCrudRepository;
import dev.blackshark.api.persistance.repository.pagin.IMedicoPaginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private final IMedicoCrudRepository medicoCrudRepository;

    @Autowired
    private final IMedicoPaginRepository medicoPaginRepository;

    public MedicoService(IMedicoCrudRepository medicoCrudRepository, IMedicoPaginRepository medicoPaginRepository) {
        this.medicoCrudRepository = medicoCrudRepository;
        this.medicoPaginRepository = medicoPaginRepository;
    }


    /*
    public NewMedicoRegistrarDTO save(NewMedicoRegistrarDTO medicoRegistrerDTO) {
        try {
            // validacion en los campos
            if(medicoRegistrerDTO.getNombre().isEmpty() || medicoRegistrerDTO.getNombre().describeConstable().isEmpty() || medicoRegistrerDTO.getNombre().equals(" ") || medicoRegistrerDTO.getNombre().isBlank()) {
                throw new RuntimeException("El nombre no puede ser vacio");
            }
            if(medicoRegistrerDTO.getDni().isEmpty() || medicoRegistrerDTO.getDni().describeConstable().isEmpty() || medicoRegistrerDTO.getDni().equals(" ") || medicoRegistrerDTO.getDni().isBlank()) {
                throw new RuntimeException("El dni no puede ser vacio");
            }

            Medico medico = new Medico(medicoRegistrerDTO);
            return medicoCrudRepository.save(medico);

        } catch (Exception e ) {
            throw new RuntimeException(e.getMessage());
        }
    }
    */

    /* Metodo save */

    public void save(NewMedicoRegistrarDTO medicoRegistrerDTO) {
        try {
            // validacion en los campos
            if(medicoRegistrerDTO.getNombre().isEmpty() || medicoRegistrerDTO.getNombre().describeConstable().isEmpty() || medicoRegistrerDTO.getNombre().equals(" ") || medicoRegistrerDTO.getNombre().isBlank()) {
                throw new RuntimeException("El nombre no puede ser vacio");
            }
            if(medicoRegistrerDTO.getDni().isEmpty() || medicoRegistrerDTO.getDni().describeConstable().isEmpty() || medicoRegistrerDTO.getDni().equals(" ") || medicoRegistrerDTO.getDni().isBlank()) {
                throw new RuntimeException("El dni no puede ser vacio");
            }

            Medico medico = new Medico(medicoRegistrerDTO);
            medicoCrudRepository.save(medico);

        } catch (Exception e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Page<MostrarMedioDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombre").ascending());

        Page<Medico> medicoPage = medicoPaginRepository.findAll(pageable);

        List<MostrarMedioDTO> mostrarMedicos = medicoPage.getContent()
                .stream()
                .map(this::mapMedicoToMostrarMedioDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(mostrarMedicos, pageable, medicoPage.getTotalElements());
    }

    public MostrarMedioDTO findByDni(String dni) {
        try {
            if(dni.isEmpty() || dni.describeConstable().isEmpty() || dni.equals(" ") || dni.isBlank()) {
                throw new RuntimeException("El dni no puede ser vacio");
            }

            Medico medico = medicoCrudRepository.findByDni(dni);
            return mapMedicoToMostrarMedioDTO(medico);
        } catch (Exception e ) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public MostrarMedioDTO findByNombre(String nombre) {
        try {
            if(nombre.isEmpty() || nombre.describeConstable().isEmpty() || nombre.equals(" ") || nombre.isBlank()) {
                throw new RuntimeException("El nombre no puede ser vacio");
            }

            Medico medico = medicoCrudRepository.findByNombreIgnoreCase(nombre);
            return mapMedicoToMostrarMedioDTO(medico);
        } catch (Exception e ) {
            throw new RuntimeException(e.getMessage());
        }
    }


    private MostrarMedioDTO mapMedicoToMostrarMedioDTO(Medico medico) {
        return new MostrarMedioDTO(
                medico.getNombre(),
                medico.getEspecialidad(),
                medico.getDni(),
                medico.getEmail()
        );
    }

    // creando un metodo para actualizar por dni
    public void updateByDni(String dni, NewMedicoRegistrarDTO medicoRegistrerDTO) {
        Medico medico = medicoCrudRepository.findByDni(dni);
        medico.setNombre(medicoRegistrerDTO.getNombre());
        medico.setEspecialidad(medicoRegistrerDTO.getEspecialidad());
        medico.setEmail(medicoRegistrerDTO.getEmail());
        medicoCrudRepository.save(medico);
    }

    // eliminar por Id = long y dni String
    public void deleteByIdAndDni(Long id, String dni) {
        try {
            if(id.describeConstable().isEmpty()) {
                throw new RuntimeException("El id no puede ser vacio");
            }
            if(dni.describeConstable().isEmpty()) {
                throw new RuntimeException("El dni no puede ser vacio");
            }

            medicoCrudRepository.deleteByIdAndDni(id, dni);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
