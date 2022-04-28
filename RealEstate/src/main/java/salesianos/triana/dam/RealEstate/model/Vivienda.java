package salesianos.triana.dam.RealEstate.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;
import salesianos.triana.dam.RealEstate.model.enumeracion.Tipo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Vivienda implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String titulo, descripcion, avatar, latLng, direccion, codPostal, poblacion, provincia;

    @Enumerated (EnumType.STRING)
    private Tipo tipo;

    private Boolean tienePiscina, tieneAscensor, tieneGaraje;

    private Integer numHabitaciones, numBanos;

    private Float precio;

    private Double metrosCuadrados;


    //ASOCIACIONES//
    @ManyToOne
    @Nullable
    private Inmobiliaria inmobiliaria;

    @Builder.Default
    @OneToMany(mappedBy = "vivienda")
    private List<Interesa> intereses = new ArrayList<>();

    @ManyToOne
    private Usuario propietario;

    // HELPERS INMOBILIARIA

    public void addToInmobiliaria(Inmobiliaria i){
        this.inmobiliaria=i;
        i.getViviendas().add(this);
    }

    public void removeToInmobiliaria(Inmobiliaria i){
        this.inmobiliaria=null;
        i.getViviendas().remove(this);
    }

    // HELPERS PROPIETARIO

    public void addToPropietario(Usuario u){
        this.propietario=u;
        u.getViviendas().add(this);
    }

    public void removeToPropietario(Usuario u){
        this.propietario=null;
        u.getViviendas().remove(this);
    }
}
