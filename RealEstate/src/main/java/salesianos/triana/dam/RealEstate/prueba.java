package salesianos.triana.dam.RealEstate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import salesianos.triana.dam.RealEstate.dto.inmobiliariaDto.GetInmobiliariasDto;
import salesianos.triana.dam.RealEstate.dto.inmobiliariaDto.GetInmobiliasDtoConverter;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.CreateUsuarioDto;
import salesianos.triana.dam.RealEstate.dto.usuarioEntidadDto.UsuarioDtoConverter;
import salesianos.triana.dam.RealEstate.model.Inmobiliaria;
import salesianos.triana.dam.RealEstate.model.Usuario;
import salesianos.triana.dam.RealEstate.model.Vivienda;
import salesianos.triana.dam.RealEstate.model.enumeracion.Tipo;
import salesianos.triana.dam.RealEstate.service.*;

import javax.annotation.PostConstruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class prueba {

    private final AdminService adminService;
    private final UsuarioEntiadService usuarioService;
    private final GestorService gestorService;
    private final InmobiliariaService inmobiliariaService;
    private final ViviendaService viviendaService;
    private final GetInmobiliasDtoConverter inmoConver;
    private final UsuarioDtoConverter usuarioDtoConverter;

    @PostConstruct
    public void prueba(){

        CreateUsuarioDto admin0 = CreateUsuarioDto.builder()
                .nombre("ManuAdmin")
                .apellidos("Fernandez")
                .email("soyadmin@gmail.com")
                .password("admin")
                .password2("admin")
                .build();

        adminService.save(admin0);

        Usuario prop0 = Usuario.builder()
                .nombre("Juan")
                .apellidos("Perez")
                .email("jupe@gmail.com")
                .password("ensalada")
                .build();

        usuarioService.save(prop0);

        GetInmobiliariasDto inmo = GetInmobiliariasDto.builder()
                .nombre("Inmobiliaria Mairena del Aljarafe")
                .telefono("654 851 254")
                .email("mairenaAljarafe@gmail.com")
                .build();

        Inmobiliaria n = inmoConver.dtoToInmobiliaria(inmo);

       inmobiliariaService.save(n);
/*
        CreateUsuarioDto gest0 = CreateUsuarioDto.builder()
                .nombre("Antonio")
                .apellidos("Sánchez")
                .email("antoGestor@gmail.com")
                .password("cachopo")
                .password2("cachopo")
                .build();

        gestorService.save(gest0);
        Usuario gN = usuarioDtoConverter.convertGetUsuarioDtoToUsuario(gest0);

        gN.addInmobiliariaToUser(n);
        inmobiliariaService.edit(n);
*/

        Vivienda vi0 = Vivienda.builder()
                .titulo("Casa fea")
                .codPostal("41927")
                .direccion("La calle más fea de España")
                .propietario(prop0)
                .precio(15F)
                .metrosCuadrados(40.0)
                .numBanos(2)
                .descripcion("La casa más fea que has visto nunca")
                .numHabitaciones(4)
                .tieneAscensor(false)
                .tieneGaraje(true)
                .tienePiscina(false)
                .latLng("-0.228021, 15.827659")
                .tipo(Tipo.Venta)
                .build();

        viviendaService.save(vi0);

    }
}

