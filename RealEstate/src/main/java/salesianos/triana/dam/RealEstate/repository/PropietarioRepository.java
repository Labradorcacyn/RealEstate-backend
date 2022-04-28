package salesianos.triana.dam.RealEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salesianos.triana.dam.RealEstate.model.Usuario;

import java.util.UUID;

public interface PropietarioRepository extends JpaRepository<Usuario, UUID> {
 /*   @Query("""
            select new salesianos.triana.dam.RealEstate.dto.GetPropietarioConViviendaDto(
            p.nombre,p.apellidos,p.direccionPropietario, p.email, p.telefono,p.avatarPropietario,v.titulo, v.avatar)
            from Propietario p LEFT JOIN p.viviendas v
            where p.id = :id
            """)
    Optional<GetPropietarioConViviendaDto> detallesPropietarioVivienda(@Param("id") Long id);*/
}
