package salesianos.triana.dam.RealEstate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.repository.InteresadoRepository;
import salesianos.triana.dam.RealEstate.service.base.BaseService;

import java.util.List;
import java.util.UUID;

@Service
public class InteresadoService extends BaseService<Usuario, UUID, InteresadoRepository> {

    @Autowired
    InteresadoRepository interesadoRepository;

    public List<Usuario> allInteresado(){
        return interesadoRepository.allInteresados();
    };

    public List<Usuario> getInteresados() {return interesadoRepository.findByIdNotNull();}

}
