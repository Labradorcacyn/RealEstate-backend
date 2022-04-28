package salesianos.triana.dam.RealEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import salesianos.triana.dam.RealEstate.model.Inmobiliaria;

import java.util.Optional;
import java.util.UUID;

public interface InmobiliariaRepository extends JpaRepository<Inmobiliaria, UUID> {

   Optional<Inmobiliaria> findByEmail(String email);

}
