package proyecto.bookadvisor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class LibroDTO {
    private long id;
    private String titulo;
    private int anho;
    private String idioma;
    private String generoNombre;
    private String autor;
    private String archivo;
}
