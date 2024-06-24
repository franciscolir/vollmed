package med.voll.api.repository;

import med.voll.api.domain.medico.Especialidad;
import med.voll.api.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);



    @Query("select m from Medico m where m.activo= true and m.especialidad=:especialidad and m.id not in(select c.medico.id from Consulta c where c.fecha=:fecha and c.activo= true) order by rand() limit 1")
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);

    @Query ("""
        SELECT m.activo
        FROM Medico m
        WHERE m.id=:idMedico
        """)
    Boolean findAllActivoById(Long idMedico);
}

    ///@Query("select m from Medico m where m.activo = 1 and m.especialidad = :especialidad and m.id not in (select c.medico.id from Consulta c where c.fecha = :fecha) order by function('RAND') limit 1")
    //Medico seleccionarMedicoConEspecialidadEnFecha(@Param("especialidad") Especialidad especialidad, @Param("fecha") LocalDateTime fecha);


