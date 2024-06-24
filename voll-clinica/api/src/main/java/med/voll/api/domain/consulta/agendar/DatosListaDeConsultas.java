package med.voll.api.domain.consulta.agendar;

import java.time.LocalDateTime;

public record DatosListaDeConsultas (

            Long id,
            Long idPaciente,
            Long idMedico,
            LocalDateTime fecha) {

        public DatosListaDeConsultas(Consulta consulta) {
            this (consulta.getId(), consulta.getMedico().getId(),consulta.getPaciente().getId(),consulta.getFecha());
        }
    }

