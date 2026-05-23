package proyecto.bookadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import proyecto.bookadvisor.domain.Libro;
import proyecto.bookadvisor.domain.Usuario;
import proyecto.bookadvisor.domain.Valoracion;
import proyecto.bookadvisor.repository.ValoracionRepository;

@Service
public class ValoracionService {
    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private UserService userService;

    public Valoracion anhdir(Valoracion valoracion) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            // identificar user
            String currentUserName = authentication.getName();
            System.out.println("*****" + currentUserName + "*****");
            Usuario user = userService.obtenerNombre(currentUserName);

            // verificar que no exista una valoracion
            if (valoracionRepository.findByLibroIdAndUsuarioId(valoracion.getLibro().getId(), user.getId()) == null) {
                valoracion.setUsuario(userService.obtenerNombre(currentUserName));
                valoracionRepository.save(valoracion);
                return valoracion;
            }
        }
        throw new RuntimeException("Solo puedes valorar una vez el libro");
    }

    public List<Valoracion> buscarPorLibro(Libro libro) {
        return valoracionRepository.findByLibro(libro);
    }

    public List<Valoracion> buscarPorLibroId(Long id) {
        return valoracionRepository.findByLibroId(id);
    }

    public void borrar( Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            // identificar user
            String currentUserName = authentication.getName();
            String currentUserRol = authentication.getAuthorities().toString();

            // obtener valoracion
            Valoracion valoracion = valoracionRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
            
            if (valoracion.getUsuario().getNombre().equals(currentUserName) || currentUserRol.equals("[ROLE_Administrador]"))  {
                System.out.println("cumple");
                valoracionRepository.deleteById(id);
            }else {
                throw new RuntimeException("Solo puedes borrar valoraciones propias");
            }

        } else {
            throw new RuntimeException("Usuario no autenticado");
        }
    }
}
