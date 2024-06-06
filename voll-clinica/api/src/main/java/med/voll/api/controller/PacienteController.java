package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedicos;
import med.voll.api.medico.DatosListadoPacientes;
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
}