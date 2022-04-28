package salesianos.triana.dam.RealEstate.dto.sharedDto;

import org.springframework.stereotype.Component;
import salesianos.triana.dam.RealEstate.dto.viviendaDto.DetailViviendaDto;
import salesianos.triana.dam.RealEstate.model.Usuario;

@Component
public class PropietarioViviendaDtoConverter {
    public PropietarioViendaDto propietarioToPropietarioVviendaDto(Usuario p){
    return PropietarioViendaDto.builder()
            .id(p.getId())
            .nombre(p.getNombre())
            .apellidos(p.getApellidos())
            .direccionPropietario(p.getDireccion())
            .email(p.getEmail())
            .telefono(p.getTelefono())
            .avatarPropietario(p.getAvatar())
            .vivienda(p.getViviendas().stream().map(v->
                    new DetailViviendaDto(v.getId(),v.getTitulo(),v.getDireccion(),v.getAvatar())).toList())
            .build();
    }
}
