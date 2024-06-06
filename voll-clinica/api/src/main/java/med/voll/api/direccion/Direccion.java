package med.voll.api.direccion;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {

    private String calle;
    private String numero;
    private String complemento;
    private String distrito;
    private String ciudad;

    public Direccion(DatosDireccion datos) {
        this.calle = datos.calle();
        this.numero = datos.numero();
        this.complemento = datos.complemento();
        this.distrito = datos.distrito();
        this.ciudad = datos.ciudad();
    }
}
