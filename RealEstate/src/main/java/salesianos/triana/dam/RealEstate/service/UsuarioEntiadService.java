package salesianos.triana.dam.RealEstate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.CreateUsuarioDto;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.model.enumeracion.UserRole;
import salesianos.triana.dam.RealEstate.repository.UsuarioEntiadRepository;
import salesianos.triana.dam.RealEstate.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("usuarioDetallesService")
@RequiredArgsConstructor
public class UsuarioEntiadService extends BaseService<Usuario, UUID, UsuarioEntiadRepository> implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.repository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(email + "no encontrado"));
    }

    public List<Usuario> loadByUserRole(UserRole role) throws NotFoundException{
        return this.repository.findAllByRole(role);
    }

/*
    @Override
    public List<Usuario> findAll() {

        List <Usuario> nuevaL = new ArrayList<>();

        repository.findAll().stream().map(x -> {
            if (x.getRole().equals(role)) {
                nuevaL.add(x);
            }

        });
        return nuevaL;
    }
*/
    public Usuario save(CreateUsuarioDto newUsuario){
        if (newUsuario.getPassword().contentEquals(newUsuario.getPassword2())){
            Usuario u = Usuario.builder()
                    .password(passwordEncoder.encode(newUsuario.getPassword()))
                    .avatar(newUsuario.getAvatar())
                    .nombre(newUsuario.getNombre())
                    .apellidos(newUsuario.getApellidos())
                    .email(newUsuario.getEmail())
                    .role(UserRole.PROPIETARIO)
                    .build();
            return save(u);
        } else {
            return null;
        }
    }


}
