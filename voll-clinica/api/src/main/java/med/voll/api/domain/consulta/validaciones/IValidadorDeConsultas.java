package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.agendar.DatosDetalleConsulta;

public interface IValidadorDeConsultas {
   // public void validar(DatosAgendarConsulta datos);

       <T extends IDatosConsulta> DatosDetalleConsulta validar(T datos);
    }