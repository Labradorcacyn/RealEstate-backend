
package salesianos.triana.dam.RealEstate.dto.interesadoDto;

import org.springframework.stereotype.Component;
import salesianos.triana.dam.RealEstate.model.Usuario;

@Component
public class GetInteresadosDtoConverter {
    public GetInteresadosDto InteresadoToGetInteresadosDto(Usuario i){
        return GetInteresadosDto.builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .apellidos(i.getApellidos())
                .build();
    }
}