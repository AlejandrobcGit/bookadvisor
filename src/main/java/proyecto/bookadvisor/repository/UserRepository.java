package proyecto.bookadvisor.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.bookadvisor.domain.Usuario;

public interface UserRepository  extends JpaRepository<Usuario,Long>{
    Usuario findByNombre (String nombre);
}
