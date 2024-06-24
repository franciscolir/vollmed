package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.agendar.DatosDetalleConsulta;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta  implements IValidadorDeConsultas {
    @Autowired
    private ConsultaRepository repository;

    public <T extends IDatosConsulta> DatosDetalleConsulta validar (T datos){

        if (datos.getIdMedico()==null)
            return null;

        var medicoConConsulta = repository.existsByMedicoIdAndFechaAndActivoTrue(datos.getIdMedico(),datos.getFecha());

        if(medicoConConsulta){
            throw new ValidationException("Medico ya tiene consulta agendada en ese horario");
        }
        return null;
    }


}
