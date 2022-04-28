package salesianos.triana.dam.RealEstate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import salesianos.triana.dam.RealEstate.model.Inmobiliaria;
import salesianos.triana.dam.RealEstate.repository.InmobiliariaRepository;
import salesianos.triana.dam.RealEstate.service.base.BaseService;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class InmobiliariaService extends BaseService<Inmobiliaria, UUID, InmobiliariaRepository> {

    private final ViviendaService viviendaService;

    public Inmobiliaria loadInmobiliariaByEmail(String email) throws NotFoundException {
        return this.repository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException(email + "no encontrado"));
    }

    @Override
    public void delete(Inmobiliaria inmobiliaria) {

        if (!inmobiliaria.getViviendas().isEmpty()) {
            inmobiliaria.getViviendas().stream().map(v -> {
                v.setInmobiliaria(null);
                return v;
            }).forEach(viviendaService::save);

        }

        super.delete(inmobiliaria);

    }
}
