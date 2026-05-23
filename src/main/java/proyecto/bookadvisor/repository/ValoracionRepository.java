package proyecto.bookadvisor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.bookadvisor.domain.Libro;
import proyecto.bookadvisor.domain.Valoracion;

public interface ValoracionRepository extends JpaRepository <Valoracion,Long>{
    List<Valoracion> findByLibro (Libro libro);
    List<Valoracion> findByLibroId (Long id);
    Valoracion findByLibroIdAndUsuarioId (Long libro_id,Long usuario_id);
}
