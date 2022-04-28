package salesianos.triana.dam.RealEstate.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class Interesa implements Serializable {

    @Builder.Default
    @EmbeddedId
    private InteresaPK id = new InteresaPK();

    @CreatedDate
    private LocalDateTime createdDate;
    @Lob
    private String mensaje;

    //ASOCIACIONES//

    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name = "vivienda_id")
    private Vivienda vivienda;

    @ManyToOne
    @MapsId("usuario_id")
    @JoinColumn(name = "usuario_id")
    private Usuario interesado;

    //HELPERS

    public void removeInteresa(Vivienda v, Usuario u) {
        v.getIntereses().remove(this);
        this.vivienda = null;
        u.getIntereses().remove(this);
        this.interesado = null;
    }

    public void addInteresado(Usuario u, Vivienda v) {
        this.interesado = u;
        this.vivienda = v;
        u.getIntereses().add(this);
        v.getIntereses().add(this);
    }
}
