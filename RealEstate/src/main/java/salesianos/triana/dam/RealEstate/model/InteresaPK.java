package salesianos.triana.dam.RealEstate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class InteresaPK implements Serializable {

    private UUID vivienda_id;

    private UUID usuario_id;
}
