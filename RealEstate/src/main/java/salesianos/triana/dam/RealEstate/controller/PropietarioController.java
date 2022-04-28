package salesianos.triana.dam.RealEstate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salesianos.triana.dam.RealEstate.dto.propietarioDto.GetPropietarioDto;
import salesianos.triana.dam.RealEstate.dto.propietarioDto.GetPropietarioDtoConverter;
import salesianos.triana.dam.RealEstate.dto.propietarioDto.PropietarioEscuetoDto;
import salesianos.triana.dam.RealEstate.dto.propietarioDto.PropietarioEscuetoDtoConverter;
import salesianos.triana.dam.RealEstate.dto.sharedDto.PropietarioViendaDto;
import salesianos.triana.dam.RealEstate.dto.sharedDto.PropietarioViviendaDtoConverter;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.GetUsuarioDto;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.UsuarioDtoConverter;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.model.enumeracion.UserRole;
import salesianos.triana.dam.RealEstate.service.PropietarioService;
import salesianos.triana.dam.RealEstate.service.UsuarioEntiadService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/propietario")
@Tag(name = "Controlador de los controller")
public class PropietarioController {

    private final PropietarioService propietarioService;
    private final GetPropietarioDtoConverter getPropietarioDtoConverter;
    private final PropietarioEscuetoDtoConverter propietarioEscuetoDtoConverter;
    private final PropietarioViviendaDtoConverter propietarioViviendaDtoConverter;
    private final UsuarioEntiadService usuarioEntiadService;
    private final UsuarioDtoConverter usuarioDtoConverter;

    @Operation(summary = "Devuelve la lista de propietarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado todos los propietarios",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la lista de propietarios",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<?> getAllProp(){
        List<Usuario> lista = usuarioEntiadService.loadByUserRole(UserRole.PROPIETARIO);
        if(lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            List<GetUsuarioDto> listadto = lista.stream().map(x -> usuarioDtoConverter.convertUsuarioToGetUsuarioDto(x)).toList();
            return ResponseEntity.ok().body(listadto);
        }
    }

    @Operation(summary = "Devuelve los detalles de un propietario y su lista de viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el propietario",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra el propietario",
                    content = @Content)
    })
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity <PropietarioViendaDto> getDetailPropietario(@PathVariable ("id") UUID id, @AuthenticationPrincipal Usuario u){
        Optional<Usuario> p = propietarioService.findById(id);
        if(p.isEmpty())
            return ResponseEntity.noContent().build();
            if (!u.getId().equals(id) && u.getRole()!=UserRole.ADMIN){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        PropietarioViendaDto propietarioViendaDto = propietarioViviendaDtoConverter.propietarioToPropietarioVviendaDto(p.get());
        return ResponseEntity.ok().body(propietarioViendaDto);

    }

    @Operation(summary = "Devuelve la lista de propietarios SIN paginar y de manera escueta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado los propietarios",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la lista de propietarios",
                    content = @Content)
    })
    @GetMapping("/lista")
    public ResponseEntity<List<PropietarioEscuetoDto>> allInteresado(){
        List<Usuario> propietarios= propietarioService.findAll();
        if (propietarios.isEmpty())
            return  ResponseEntity.notFound().build();
        return ResponseEntity.ok().body( propietarios.stream().
                map(propietarioEscuetoDtoConverter::propietarioToPropietarioEscuetoDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Borra un propietario y sus viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha borrado con exito",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra el propietario",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePropietario(@PathVariable("id")UUID idProp, @AuthenticationPrincipal Usuario u) {
        if (propietarioService.findById(idProp).isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            if (!u.getId().equals(idProp) && u.getRole()!=UserRole.ADMIN){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            propietarioService.deleteById(idProp);
            return ResponseEntity.ok().build();
        }
    }
}