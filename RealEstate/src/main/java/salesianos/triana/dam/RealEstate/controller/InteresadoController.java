package salesianos.triana.dam.RealEstate.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import salesianos.triana.dam.RealEstate.dto.interesadoDto.*;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.model.enumeracion.UserRole;
import salesianos.triana.dam.RealEstate.service.InteresadoService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interesado")
@Tag(name = "Controlador de los interesados")
public class InteresadoController {

    private final InteresadoService interesadoService;
    private final GetInteresadosListaDtoConverter getInteresadosListaDtoConverter;
    private final GetInteresadosDtoConverter dtoConverter;
    private final DetailInteresadoDtoConverter detailInteresadoDtoConverter;

    @Operation(summary = "Muestra la lista de todos los interesados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
            description = "No se ha encontrado ningun interesado",
            content = @Content),
            @ApiResponse(responseCode = "200",
            description = "Se han encontrado todos los interesados",
            content = {
                    @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = GetInteresadosListaDto.class)))
            })
    })
    @GetMapping("/")
    public ResponseEntity<List<GetInteresadosListaDto>> getAllInteresados() {
        List<Usuario> lista = interesadoService.getInteresados();
        if(lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<GetInteresadosListaDto> dtos = lista.stream().map(getInteresadosListaDtoConverter::interesadoToGetInteresadosListaDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Encuentra y devuelve un interesado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
            description = "No se ha encontrado al interesado",
            content = @Content),
            @ApiResponse(responseCode = "200",
            description = "Se ha encontrado al interesado",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DetailInteresadoDto.class))
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetailInteresadoDto> getInteresado(@PathVariable UUID id, @AuthenticationPrincipal Usuario u) {
        if(interesadoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (!u.getId().equals(id) && u.getRole()!= UserRole.PROPIETARIO){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        DetailInteresadoDto dto = detailInteresadoDtoConverter.interesadoToDetailInteresadoDto(interesadoService.findById(id).get());
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Obtiene la lista de interesados")
    @GetMapping("/count")
    public List<GetInteresadosDto> allInteresados(){
        GetInteresadosDto allInteresados = new GetInteresadosDto();
        return interesadoService.findAll().stream().map(dtoConverter::InteresadoToGetInteresadosDto).collect(Collectors.toList());
    }
}