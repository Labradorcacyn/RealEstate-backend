package salesianos.triana.dam.RealEstate.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import salesianos.triana.dam.RealEstate.model.Usuario;

import java.util.List;
import java.util.UUID;

public interface InteresadoRepository extends JpaRepository<Usuario, UUID> {

    @Query("select i from Usuario i")
    public List<Usuario> allInteresados();

    @EntityGraph(value = "grafo-interesado", type = EntityGraph.EntityGraphType.FETCH)
    List<Usuario> findByIdNotNull();

}
