package salesianos.triana.dam.RealEstate.dto.sharedDto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import salesianos.triana.dam.RealEstate.model.enumeracion.Tipo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Builder
public class ViviendaPropietarioDto {


    // Vivienda
    private String titulo, descripcion, avatar, latLng, direccion, codPostal, poblacion, provincia;
    private Boolean tienePiscina, tieneAscensor, tieneGaraje;
    private Integer numHabitaciones, numBanos;
    private Float precio;
    private Double metrosCuadrados;
    private Tipo tipo;

    // Propietario
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String nombre,apellidos,direccionPropietario,email,telefono,avatarPropietario;

    // Inmobiliaria
    private String inmobiliariaNombre;
}
