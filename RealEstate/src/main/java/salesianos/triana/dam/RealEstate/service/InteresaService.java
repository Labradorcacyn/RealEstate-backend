package salesianos.triana.dam.RealEstate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.RealEstate.model.Interesa;
import salesianos.triana.dam.RealEstate.model.InteresaPK;
import salesianos.triana.dam.RealEstate.repository.InteresaRepository;
import salesianos.triana.dam.RealEstate.service.base.BaseService;

import java.util.List;
import java.util.UUID;

@Service
public class InteresaService extends BaseService<Interesa, InteresaPK, InteresaRepository> {

    @Autowired
    InteresaRepository interesaRepository;
    public List<Interesa> allInteresaDeUnaVivienda(UUID id){
        return interesaRepository.allInteresaDeUnaVivienda(id);
    }

}
