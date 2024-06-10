package med.voll.api.domain.direccion;


import jakarta.persistence.Embeddable;
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

    public void actualizarInformacion(DatosActializacionDrieccion direccion) {
        if (direccion.calle() != null)
            this.calle = direccion.calle();
        if (direccion.numero() != null)
            this.numero = direccion.numero();
        if (direccion.complemento() != null)
            this.complemento = direccion.complemento();
        if (direccion.distrito() != null)
            this.distrito = direccion.distrito();
        if (direccion.ciudad() != null)
            this.ciudad = direccion.ciudad();
    }
}
