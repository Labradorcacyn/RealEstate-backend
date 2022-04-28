package salesianos.triana.dam.RealEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import salesianos.triana.dam.RealEstate.model.Vivienda;

import java.util.UUID;

public interface ViviendaRepository extends JpaRepository<Vivienda, UUID>, JpaSpecificationExecutor<Vivienda> {

}
