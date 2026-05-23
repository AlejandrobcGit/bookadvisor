package proyecto.bookadvisor.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.bookadvisor.clases.FormInfo;
import proyecto.bookadvisor.domain.Genero;
import proyecto.bookadvisor.domain.Libro;
import proyecto.bookadvisor.dto.LibroDTO;
import proyecto.bookadvisor.repository.LibroRepository;

@Service
public class BookadvisorService {

        @Autowired
        private FileStorageService fileStorageService;

        private List<FormInfo> quejas = new ArrayList<FormInfo>();

        @Autowired
        private LibroRepository libroRepository;

        public void guardarQueja(FormInfo formInfo) {
                quejas.add(formInfo);
        }

        public List<FormInfo> cargarDatos() {
                return quejas;
        }

        // CRUD

        public Libro anhadirLibro(Libro libro) {
                List<Libro> libroExite = libroRepository.findByTituloAndAnho(libro.getTitulo(), libro.getAnho());
                if (!libroExite.isEmpty()) {
                        throw new RuntimeException("Libro ya registrado");
                }
                libroRepository.save(libro);
                return libro;
        }

        public List<Libro> buscarTodos() {
                return libroRepository.findAll();
        }

        public Libro buscarID(Long id) {
                return libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        }

        public void editarLibro(Libro libro) {
                buscarID(libro.getId()); // vaidar que existe
                libroRepository.save(libro);
        }

        public Libro borrar(long id) {
                Libro libro = this.buscarID(id);
                System.out.println("libro: " + libro);
                fileStorageService.delete(libro.getArchivo());
                libroRepository.delete(libro);
                return libro;
        }

        public List<LibroDTO> buscar(String textoTitulo, Genero genero) {
                textoTitulo = (textoTitulo != null) ? textoTitulo.toLowerCase() : "";
                List<Libro> encontrados = new ArrayList<>();

                for (Libro libro : buscarTodos()) {
                        boolean nombreCoincide = textoTitulo.isBlank()
                                        || libro.getTitulo().toLowerCase().contains(textoTitulo);
                        boolean generoCoincide = genero == null ||  libro.getGenero().equals(genero);

                        if (nombreCoincide && generoCoincide) {
                                encontrados.add(libro);
                        }
                }

                return convertLibroToDto(encontrados);
        }

        @Autowired
        private ModelMapper modelMapper;

        public List<LibroDTO> convertLibroToDto( List<Libro> listaLibros ) {
                   List<LibroDTO> listaLibroDTO = new ArrayList<>();
                for (Libro libro : listaLibros)
                        listaLibroDTO.add(modelMapper.map(libro, LibroDTO.class));
                return listaLibroDTO;
        }

}
