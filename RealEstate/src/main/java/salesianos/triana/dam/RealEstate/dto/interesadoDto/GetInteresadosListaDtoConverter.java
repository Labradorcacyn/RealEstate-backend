package salesianos.triana.dam.RealEstate.dto.interesadoDto;

import org.springframework.stereotype.Component;
import salesianos.triana.dam.RealEstate.model.Usuario;

@Component
public class GetInteresadosListaDtoConverter {

    public GetInteresadosListaDto interesadoToGetInteresadosListaDto(Usuario i) {
        return GetInteresadosListaDto.builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .apellidos(i.getApellidos())
                .direccion(i.getDireccion())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .avatar(i.getAvatar())
                .build();
    }

}