package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.agendar.DatosDetalleConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica  implements IValidadorDeConsultas {

    public <T extends IDatosConsulta> DatosDetalleConsulta validar(T datos){

        var domingo = DayOfWeek.SUNDAY.equals(datos.getFecha().getDayOfWeek());
        var antesDeApertura = datos.getFecha().getHour()<7;
        var despuesDeCierre =datos.getFecha().getHour()>19;
        if(domingo||antesDeApertura||despuesDeCierre){
            throw new ValidationException("El horario de atencion es de Lunes a Sabado de 07:00 a 19:00 horas");
        }
        return null;
    }
}
