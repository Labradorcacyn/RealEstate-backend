package salesianos.triana.dam.RealEstate.service;

import org.springframework.stereotype.Service;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.repository.PropietarioRepository;
import salesianos.triana.dam.RealEstate.service.base.BaseService;

import java.util.UUID;

@Service
public class PropietarioService extends BaseService<Usuario, UUID, PropietarioRepository> {

 /*   @Autowired
    private PropietarioRepository repo;

    public Optional<GetPropietarioConViviendaDto> getPropietarioDetailVivienda(@Param("id") Long id){
        return repo.detallesPropietarioVivienda(id);
    }*/


}
