package dev.blackshark.api.persistance.repository.crud;

import dev.blackshark.api.persistance.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicoCrudRepository extends JpaRepository<Medico, Long> {


    // metodo findByDni
    public Medico findByDni(String dni);

    // eliminar por Id = long y dni String
    public void deleteByIdAndDni(Long id, String dni);

    // buscar por nombre ignore case
    public Medico findByNombreIgnoreCase(String nombre);
}
