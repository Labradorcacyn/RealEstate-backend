package salesianos.triana.dam.RealEstate.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

public class FiltroVivienda {

    // fechaCumple<1997

    private String key; // fechaCumple
    private String operation; // <
    private Object value; // 1997
}
