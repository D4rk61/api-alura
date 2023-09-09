package dev.blackshark.api.web.controller;

import dev.blackshark.api.domain.dto.DatosRequestMedico;
import dev.blackshark.api.domain.dto.NewMedicoRegistrarDTO;
import dev.blackshark.api.domain.dto.MostrarMedioDTO;
import dev.blackshark.api.domain.service.MedicoService;
import dev.blackshark.api.persistance.entity.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    private static final String PAGE_NUMBER = "0";
    private static final String PAGE_SIZE = "10";

    @Autowired
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }


    public void save(NewMedicoRegistrarDTO medicoRegistrerDTO) {
        try {
            this.medicoService.save(medicoRegistrerDTO);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @GetMapping("/listAll")
    public ResponseEntity<Page<MostrarMedioDTO>> findAll(
            @RequestParam(value = "page", defaultValue = PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = PAGE_SIZE) int size) {

        return ResponseEntity.ok(this.medicoService.findAll(page, size));

    }

    @GetMapping("/findByDni")
    public ResponseEntity<MostrarMedioDTO> findByDni(@RequestParam String dni) {
        try {
            return ResponseEntity.ok(this.medicoService.findByDni(dni));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PutMapping("/updateByDni")
    public ResponseEntity<?> updateMedico(@RequestParam String dni, @RequestBody NewMedicoRegistrarDTO medicoRegistrerDTO) {
        try {
            this.medicoService.updateByDni(dni, medicoRegistrerDTO);
            return ResponseEntity.ok("actualizado");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/deleteBydata")
    public ResponseEntity<?> deleteMedico(@RequestParam Long id, @RequestParam String dni) {
        try {

            this.medicoService.deleteByIdAndDni(id, dni);
            return ResponseEntity.ok("eliminado");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
