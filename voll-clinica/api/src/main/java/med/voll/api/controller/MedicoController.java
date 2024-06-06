package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedicos;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    public void registraMedico(@RequestBody @Valid DatosRegistroMedico datos){
        System.out.println("El request llega correctamente");
        System.out.println(datos);
        repository.save(new Medico(datos));

    }

    @GetMapping
    public Page<DatosListadoMedicos> listaMedico(Pageable paginacion){
        return repository.findAll(paginacion).map(DatosListadoMedicos::new);
    }
}
