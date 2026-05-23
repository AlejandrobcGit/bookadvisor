package proyecto.bookadvisor.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity

public class Libro { 

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Indicar titulo del libro")
    private String titulo;

    @Positive(message = "indicar un año valido")
    private int anho;

    @ManyToOne 
    private Genero genero;

    @NotEmpty(message = "Indicar Autor del libro")
    private String autor;

    private Idioma idioma;

    @NotEmpty(message = "Indicar sinopsis del libro")
    private String sinopsis;
    private LocalDate fechaAlta;
    private LocalDate fechaMod;
    private String archivo;
}
