package salesianos.triana.dam.RealEstate.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import salesianos.triana.dam.RealEstate.model.enumeracion.UserRole;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class Usuario implements Serializable, UserDetails {

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

    private String nombre, apellidos, direccion, telefono, avatar, password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder.Default
    private LocalDateTime lastPasswordChangeAt = LocalDateTime.now();

    //ASOCIACIONES//

    @OneToMany(mappedBy = "interesado", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Interesa> intereses=new ArrayList<>();

    @ManyToOne
    //@MapsId("inmobiliaria_id")
    @JoinColumn(name = "inmobiliaria_id")
    private Inmobiliaria inmobiliaria;

    @OneToMany(mappedBy = "propietario", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Vivienda> viviendas = new ArrayList<>();


    //HELPER//
    public void addInmobiliariaToUser(Inmobiliaria i){
        this.inmobiliaria=i;
        i.getGestores().add(this);
    }

    public void removeInmobiliariaFromUser(Inmobiliaria i){
        this.inmobiliaria = null;
        i.getGestores().remove(this);
    }


    //OVERRIDES//

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

