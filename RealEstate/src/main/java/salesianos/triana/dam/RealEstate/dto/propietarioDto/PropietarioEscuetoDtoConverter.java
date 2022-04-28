package salesianos.triana.dam.RealEstate.dto.propietarioDto;

import org.springframework.stereotype.Component;
import salesianos.triana.dam.RealEstate.model.Usuario;

@Component
public class PropietarioEscuetoDtoConverter {

    public PropietarioEscuetoDto propietarioToPropietarioEscuetoDto(Usuario p){
        return PropietarioEscuetoDto.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellido(p.getApellidos())
                .build();
    }

}
