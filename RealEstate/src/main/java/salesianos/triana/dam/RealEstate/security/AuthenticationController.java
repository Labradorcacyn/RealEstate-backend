package salesianos.triana.dam.RealEstate.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.security.dto.JwtUsuarioResponse;
import salesianos.triana.dam.RealEstate.security.dto.LoginDto;
import salesianos.triana.dam.RealEstate.security.jwt.JwtProvider;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Devolver una respuesta aecuada que incluya el token de usuario

        String jwt = jwtProvider.generateToken(authentication);

        Usuario usuario = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertUsuarioToJwtUsuarioResponse(usuario, jwt));
    }

    @GetMapping("/me")
    public ResponseEntity<?> quienSoy(@AuthenticationPrincipal Usuario usuario){
        return ResponseEntity.ok(convertUsuarioToJwtUsuarioResponse(usuario, null));
    }

    private JwtUsuarioResponse convertUsuarioToJwtUsuarioResponse(Usuario usuario, String jwt){
        return JwtUsuarioResponse.builder()
                .nombre(usuario.getNombre())
                .apellidos(usuario.getApellidos())
                .email(usuario.getEmail())
                .avatar(usuario.getAvatar())
                .role(usuario.getRole().name())
                .token(jwt)
                .build();
    }
}
