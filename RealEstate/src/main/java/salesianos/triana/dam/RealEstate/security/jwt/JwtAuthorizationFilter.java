package salesianos.triana.dam.RealEstate.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.service.UsuarioEntiadService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UsuarioEntiadService usuarioEntiadService;
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Obtener el token de la peticion
        String token = getJwtFromRequest(request);

        // 2. Validar el token
        try{
            if (StringUtils.hasText(token) && jwtProvider.validateToken(token)){
                UUID usuarioId = jwtProvider.getUsuarioIdFromJwt(token);

                Optional<Usuario> usuario = usuarioEntiadService.findById(usuarioId);

                if (usuario.isPresent()){
                    Usuario nuevo = usuario.get();
                    UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            nuevo,
                            nuevo.getRole(),
                            nuevo.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }catch (Exception ex){
            // Informar en el log
            log.info("No se ha podido establecer el contexto de seguridad (" + ex.getMessage() + ")");
        }

        filterChain.doFilter(request, response);
        // 2.1 Si es v??lido, autenticamos al usuario

        // 2.2 Si no es v??lido, lanzamos una excepcion
    }

    private String getJwtFromRequest(HttpServletRequest request){
        // Authorization: Bearrer eltoken.qimas.megusta
        String bearerToken = request.getHeader(JwtProvider.TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProvider.TOKEN_PREFIX)){
            return bearerToken.substring(JwtProvider.TOKEN_PREFIX.length());
        }
        return null;
    }
}
