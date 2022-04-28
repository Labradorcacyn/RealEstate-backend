package salesianos.triana.dam.RealEstate.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import salesianos.triana.dam.RealEstate.model.Inmobiliaria;
import salesianos.triana.dam.RealEstate.model.Vivienda;
import salesianos.triana.dam.RealEstate.repository.ViviendaRepository;
import salesianos.triana.dam.RealEstate.service.base.BaseService;

import java.util.List;
import java.util.UUID;

@Service
public class ViviendaService extends BaseService<Vivienda, UUID, ViviendaRepository> {

    public List<Vivienda> viviendaConSpecification (Specification<Vivienda> spec){return  repository.findAll(spec);}

}
