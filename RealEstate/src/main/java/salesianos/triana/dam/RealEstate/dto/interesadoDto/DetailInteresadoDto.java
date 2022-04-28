package salesianos.triana.dam.RealEstate.dto.interesadoDto;

import lombok.*;
import salesianos.triana.dam.RealEstate.dto.viviendaDto.ViviendaListaDto;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class DetailInteresadoDto {

    private String nombre, apellidos, direccion, email, telefono, avatar;
    private List<ViviendaListaDto> viviendas;

}
