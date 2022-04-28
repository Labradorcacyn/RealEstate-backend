package salesianos.triana.dam.RealEstate.dto.viviendaDto;

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
public class ViviendaListaDto {

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

    private String titulo;
    private Tipo tipo;
    private String direccion,provincia,poblacion,avatar;
    private float precio;
    private double metrosCuadrados;
    private int numeroBanos;
    private int numeroHabitaciones;

}
