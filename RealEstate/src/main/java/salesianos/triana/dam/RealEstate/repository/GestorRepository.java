package salesianos.triana.dam.RealEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salesianos.triana.dam.RealEstate.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface GestorRepository extends JpaRepository<Usuario, UUID> {

        Optional<Usuario> findByEmail(String email);
}
