package proyecto.bookadvisor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.bookadvisor.domain.Libro;

public interface LibroRepository extends JpaRepository <Libro,Long> {
   List<Libro> findByTituloAndAnho(String titulo, int anho);    
}
