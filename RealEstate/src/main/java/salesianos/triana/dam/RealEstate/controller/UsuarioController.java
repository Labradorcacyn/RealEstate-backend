package salesianos.triana.dam.RealEstate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import salesianos.triana.dam.RealEstate.dto.propietarioDto.GetPropietarioDto;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.CreateUsuarioDto;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.GetUsuarioDto;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.UsuarioDtoConverter;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.service.AdminService;
import salesianos.triana.dam.RealEstate.service.GestorService;
import salesianos.triana.dam.RealEstate.service.InmobiliariaService;
import salesianos.triana.dam.RealEstate.service.UsuarioEntiadService;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioEntiadService usuarioEntiadService;
    private final AdminService adminService;
    private final GestorService gestorService;
    private final InmobiliariaService inmoServ;
    private final UsuarioDtoConverter usuarioDtoConverter;

    @PostMapping("/auth/register/user")
    public ResponseEntity<GetUsuarioDto> nuevoUsuario(@RequestBody CreateUsuarioDto nUs){
        Usuario guardado = usuarioEntiadService.save(nUs);

        if (guardado == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioToGetUsuarioDto(guardado));
    }

    @PostMapping("auth/register/admin")
    public ResponseEntity<GetUsuarioDto> nuevoAdmin(@RequestBody CreateUsuarioDto nUs){
        Usuario guardado = adminService.save(nUs);

        if (guardado == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioToGetUsuarioDto(guardado));
    }

    @PostMapping("auth/register/gestor")
    public ResponseEntity<GetUsuarioDto> nuevoGestor(@RequestBody CreateUsuarioDto nUs){
        Usuario guardado = gestorService.save(nUs);

        if (guardado == null)
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(usuarioDtoConverter.convertUsuarioToGetUsuarioDto(guardado));
    }
}
