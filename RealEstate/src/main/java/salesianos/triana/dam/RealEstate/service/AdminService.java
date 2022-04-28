package salesianos.triana.dam.RealEstate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.CreateUsuarioDto;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.model.enumeracion.UserRole;
import salesianos.triana.dam.RealEstate.repository.AdminRepository;
import salesianos.triana.dam.RealEstate.service.base.BaseService;

import java.util.UUID;

@Service("adminDetalleService")
@RequiredArgsConstructor
public class AdminService extends BaseService<Usuario, UUID, AdminRepository> {

    private final PasswordEncoder passwordEncoder;

    public Usuario save(CreateUsuarioDto newUsuario){
        if (newUsuario.getPassword().contentEquals(newUsuario.getPassword2())){
            Usuario u = Usuario.builder()
                    .password(passwordEncoder.encode(newUsuario.getPassword()))
                    .avatar(newUsuario.getAvatar())
                    .nombre(newUsuario.getNombre())
                    .apellidos(newUsuario.getApellidos())
                    .email(newUsuario.getEmail())
                    .role(UserRole.ADMIN)
                    .build();
            return save(u);
        } else {
            return null;
        }
    }
}
