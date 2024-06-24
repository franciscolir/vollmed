package med.voll.api.domain.consulta.agendar;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.DatosActializacionMedico;
import med.voll.api.domain.medico.DatosRegistroMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime fecha;
    private Boolean activo;

    public Consulta(DatosAgendarConsulta datos) {
        this.id = getId();
        this.medico = getMedico();
        this.paciente = getPaciente();
        this.fecha = datos.getFecha();
        this.activo = true;
    }


    public void actualizarInformacion(DatosDetalleConsulta datos) {

        if (datos.idMedico() != null)
            medico.getId();
        if (datos.idPaciente()!= null)
            paciente.getId();
        if (datos.fecha()!= null)
            this.fecha = datos.fecha();
    }

    public void cancelarHora() {
        this.activo = false;
    }

}


