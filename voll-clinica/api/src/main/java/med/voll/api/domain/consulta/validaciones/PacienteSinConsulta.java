package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.agendar.DatosDetalleConsulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta  implements IValidadorDeConsultas {
    @Autowired
    private ConsultaRepository repository;

    public <T extends IDatosConsulta> DatosDetalleConsulta validar(T datos){
        var primerHorario = datos.getFecha().withHour(7);
        var ultimoHorario = datos.getFecha().withHour(18);

        var pacienteConConsulta = repository.existsByPacienteIdAndFechaBetweenAndActivoTrue(datos.getIdPaciente(),primerHorario,ultimoHorario);

        if(pacienteConConsulta){
            throw new ValidationException("paciente ya agendo consulta");
        }
        return null;
    }
}
