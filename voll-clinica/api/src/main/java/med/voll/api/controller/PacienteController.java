package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DatosActializacionMedico;
import med.voll.api.medico.Medico;
import med.voll.api.paciente.DatosActualizacionPacientes;
import med.voll.api.paciente.DatosListadoPacientes;
import med.voll.api.paciente.DatosRegistroPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public void registraPaceinte(@RequestBody @Valid DatosRegistroPaciente datos){
        System.out.println("El request llega correctamente");
        System.out.println("Datos recibidos "+ datos);
        repository.save(new Paciente(datos));
    }

    @GetMapping
    public Page<DatosListadoPacientes> listaPaceinte(Pageable paginacion){
        return repository.findAll(paginacion).map(DatosListadoPacientes ::new);
    }
    //actualiza datos de paciente especifico
    @PutMapping
    @Transactional
    public void actualizarDatos (@RequestBody @Valid DatosActualizacionPacientes datos){
        Paciente paciente = repository.getReferenceById(datos.id());
        paciente.actualizarInformacion(datos);
    }
    //desactiva paciente (delete logico)
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico (@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.desactivarPaciente();
    }
}