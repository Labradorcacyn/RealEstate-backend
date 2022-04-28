package salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto;

import org.springframework.stereotype.Component;
import salesianos.triana.dam.RealEstate.dto.propietarioDto.GetPropietarioDto;
import salesianos.triana.dam.RealEstate.model.Usuario;

@Component
public class UsuarioDtoConverter {

    public GetUsuarioDto convertUsuarioToGetUsuarioDto(Usuario u) {
        return GetUsuarioDto.builder()
                .id(u.getId())
                .avatar(u.getAvatar())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .email(u.getEmail())
                .role(u.getRole())
                .build();
    }

    public Usuario convertGetUsuarioDtoToUsuario(CreateUsuarioDto gud){
        return Usuario.builder()
                .avatar(gud.getAvatar())
                .nombre(gud.getNombre())
                .apellidos(gud.getApellidos())
                .email(gud.getEmail())
                .build();
    }
}
