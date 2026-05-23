package proyecto.bookadvisor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.bookadvisor.domain.Genero;
import proyecto.bookadvisor.repository.GeneroRepository;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> buscartodos(){
        return generoRepository.findAll();
    }

    public Genero buscarPorId(long id){
        return generoRepository.findById(id).orElseThrow(() -> new RuntimeException("Genero no encontrado"));
    }

    public Genero anhdir(Genero genero){
        generoRepository.save(genero);
        return genero;
    }

    public Genero borrar(Long id){
        Genero genero = buscarPorId(id);
        generoRepository.delete(genero);
        return genero;
    }
}
