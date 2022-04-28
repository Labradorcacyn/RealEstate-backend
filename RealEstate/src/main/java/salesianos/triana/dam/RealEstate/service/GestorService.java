package salesianos.triana.dam.RealEstate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.CreateUsuarioDto;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.model.enumeracion.UserRole;
import salesianos.triana.dam.RealEstate.repository.GestorRepository;
import salesianos.triana.dam.RealEstate.service.base.BaseService;

import java.util.UUID;

@Service("gestorDetalleService")
@RequiredArgsConstructor
public class GestorService extends BaseService<Usuario, UUID, GestorRepository> {

    private final PasswordEncoder passwordEncoder;
    private final InmobiliariaService inmoServ;

    public Usuario save(CreateUsuarioDto newUsuario){
        if (newUsuario.getPassword().contentEquals(newUsuario.getPassword2())){
            Usuario u = Usuario.builder()
                    .password(passwordEncoder.encode(newUsuario.getPassword()))
                    .avatar(newUsuario.getAvatar())
                    .nombre(newUsuario.getNombre())
                    .apellidos(newUsuario.getApellidos())
                    .email(newUsuario.getEmail())
                    .inmobiliaria(inmoServ.loadInmobiliariaByEmail(newUsuario.getInmobiliaria()))
                    .role(UserRole.GESTOR)
                    .build();
            return save(u);
        } else {
            return null;
        }
    }
}
