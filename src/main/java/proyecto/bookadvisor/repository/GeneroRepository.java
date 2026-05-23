package proyecto.bookadvisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.bookadvisor.domain.Genero;

public interface GeneroRepository extends JpaRepository <Genero,Long>{
    
}
