package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.agendar.DatosDetalleConsulta;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo  implements IValidadorDeConsultas {
    @Autowired
    private PacienteRepository pacienteRepository;

    public <T extends IDatosConsulta> DatosDetalleConsulta validar(T datos){

        if(datos.getIdPaciente()==null){
            return null;
        }
        var pacienteActivo = pacienteRepository.findAllActivoById(datos.getIdPaciente());

    if (!pacienteActivo){
        throw new ValidationException("No se puede agendar citas a pacientes inactivos en sistema");
    }
        return null;
    }
}
