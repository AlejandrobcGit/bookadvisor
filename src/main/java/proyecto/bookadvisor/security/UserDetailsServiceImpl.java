package proyecto.bookadvisor.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import proyecto.bookadvisor.domain.Usuario;
import proyecto.bookadvisor.repository.UserRepository;

@Component 
public class UserDetailsServiceImpl implements UserDetailsService { 
 @Autowired 
 private UserRepository userRepository; 
 
 @Override 
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
    Usuario usuario = userRepository.findByNombre(username); 
    if (usuario == null)  throw (new UsernameNotFoundException("Usuario no encontrado!")); 
    return User                     //org.springframework.security.core.userdetails.User 
        .withUsername(username) 
        .roles(usuario.getRol().toString()) 
        .password(usuario.getContraseña()) 
        .build(); 
 } 
}