package med.voll.api.domain.consulta.agendar;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.consulta.validaciones.IDatosConsulta;
import med.voll.api.domain.medico.Especialidad;
import java.time.LocalDateTime;

public record DatosAgendarConsulta (

        @NotNull
        Long idPaciente,
        Long idMedico,
        @NotNull
        @Future
        LocalDateTime fecha,
        Especialidad especialidad)
        implements IDatosConsulta
        {


                @Override
                public Long getIdPaciente() {
                        return idPaciente;
                }

                @Override
                public Long getIdMedico() {
                        return idMedico;
                }

                @Override
                public LocalDateTime getFecha() {
                        return fecha;
                }

                @Override
                public Especialidad getEspecialidad() {
                        return especialidad;
                }
        }