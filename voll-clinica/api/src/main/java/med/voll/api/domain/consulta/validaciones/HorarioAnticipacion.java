package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAnticipacion  implements ValidadorDeConsultas{
    public void validar(DatosAgendarConsulta datos){

        var ahora = LocalDateTime.now();
        var horaConsulta = datos.fecha();
        var diferencia30min = Duration.between(ahora,horaConsulta).toMinutes()<30;

        if(diferencia30min){
            throw new ValidationException("Debe agendar con almenos 30 minutos de anticipacion");
        }
    }
}
