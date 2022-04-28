package salesianos.triana.dam.RealEstate.security.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class JwtUsuarioResponse {

    private String email, nombre, apellidos, avatar, role, token;
}
