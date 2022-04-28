package salesianos.triana.dam.RealEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.model.enumeracion.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioEntiadRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findAllByRole(UserRole role);
}
