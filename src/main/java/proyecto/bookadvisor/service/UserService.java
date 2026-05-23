package proyecto.bookadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import proyecto.bookadvisor.domain.Usuario;
import proyecto.bookadvisor.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario añadir(Usuario user) {
        if (user == null) {
            throw new RuntimeException("Usuario es nulo");
        }
      
        String passCrypted = passwordEncoder.encode(user.getContraseña());
        user.setContraseña(passCrypted);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Usuario ya Existe");
        }
        
    }

    public Usuario modificar(Usuario user) {
        if (user == null) {
            throw new RuntimeException("Usuario es nulo");
        }
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void borrar(Long id) {
        if (id == null) {
            throw new RuntimeException("nombre es nulo");
        }
        userRepository.deleteById(id);
    }

    public List<Usuario> obtenerTodos() {
        return userRepository.findAll();
    }

    public Usuario obtenerID(Long id) {
        if (id == null) {
            throw new RuntimeException("el ID no puede ser nulo");
        }
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("id invalido"));
    }

    public Usuario obtenerNombre(String nombre) {
        return userRepository.findByNombre(nombre);
    }
}
