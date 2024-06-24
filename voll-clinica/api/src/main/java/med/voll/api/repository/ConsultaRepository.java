package med.voll.api.repository;

import med.voll.api.domain.consulta.agendar.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByPacienteIdAndFechaBetweenAndActivoTrue(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndFechaAndActivoTrue(Long idMedico, LocalDateTime fecha);

    @Query("""
        SELECT c.activo
        FROM Consulta c
        WHERE c.id=:id
        """)
    Boolean findAllActivoById(Long id);

    Consulta getReferenceByPacienteId(Long id);


}