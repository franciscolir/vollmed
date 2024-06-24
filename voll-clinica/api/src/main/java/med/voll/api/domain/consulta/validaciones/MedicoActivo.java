package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.agendar.DatosDetalleConsulta;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo  implements IValidadorDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public <T extends IDatosConsulta> DatosDetalleConsulta validar(T datos){

        if(datos.getIdMedico()==null){
            return null;
        }
        var medicoActivo = medicoRepository.findAllActivoById(datos.getIdMedico());

        if (!medicoActivo){
            throw new ValidationException("No se puede agendar citas con medico inactivos en sistema");
        }
        return null;
    }
}


