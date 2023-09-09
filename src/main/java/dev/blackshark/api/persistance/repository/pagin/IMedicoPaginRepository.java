package dev.blackshark.api.persistance.repository.pagin;

import dev.blackshark.api.domain.dto.MostrarMedioDTO;
import dev.blackshark.api.persistance.entity.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicoPaginRepository extends ListPagingAndSortingRepository<Medico, Long> {

    Page<Medico> findAll(Pageable pageable);
}
