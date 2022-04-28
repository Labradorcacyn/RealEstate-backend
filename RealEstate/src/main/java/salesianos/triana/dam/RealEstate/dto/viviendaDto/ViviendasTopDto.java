package salesianos.triana.dam.RealEstate.dto.viviendaDto;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import salesianos.triana.dam.RealEstate.model.enumeracion.Tipo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class ViviendasTopDto {

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

    private Tipo tipo;
    private String titulo, direccion, provincia, poblacion, avatar;
    private float precio;
    private double metrosCuadrados;
    private int numeroBanos;
    private int numeroHabitaciones;
    private int numeroInteresados;

}
