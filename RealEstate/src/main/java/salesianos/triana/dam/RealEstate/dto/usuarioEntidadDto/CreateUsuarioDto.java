package salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto;

import lombok.*;
import salesianos.triana.dam.RealEstate.model.Inmobiliaria;
import salesianos.triana.dam.RealEstate.model.enumeracion.UserRole;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreateUsuarioDto {

    private String nombre, apellidos, avatar, email, password, password2, inmobiliaria;
}
