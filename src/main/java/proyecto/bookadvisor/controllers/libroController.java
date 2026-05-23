package proyecto.bookadvisor.controllers;

import java.nio.file.Paths;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import proyecto.bookadvisor.domain.FindForm;
import proyecto.bookadvisor.domain.Libro;
import proyecto.bookadvisor.service.BookadvisorService;
import proyecto.bookadvisor.service.EmailService;
import proyecto.bookadvisor.service.FileStorageService;
import proyecto.bookadvisor.service.GeneroService;

@Controller
@RequestMapping("/libros")
public class libroController {
    private int anho = LocalDate.now().getYear();

    @Autowired
    BookadvisorService bookadvisorService;

    @Autowired
    EmailService emailService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private GeneroService generoService;

    
    @GetMapping("/")
    public String ShowLibro(@RequestParam(required = false) Integer numMsg, Model model) {
        if (numMsg != null) {
            switch (numMsg) {
                case 1 -> model.addAttribute("msg", "Libro no encontrado");
                case 2 -> model.addAttribute("msg", "Formulario incorrecto");
                case 3 -> model.addAttribute("msg", "Código ya existe");
                case 4 -> model.addAttribute("msg", "Problemas con el archivo");
            }
        }
        model.addAttribute("findForm", new FindForm());
        model.addAttribute("annoActual", anho);
        // model.addAttribute("repositorio", bookadvisorService.buscarTodos());
        model.addAttribute("repositorio", bookadvisorService.convertLibroToDto(bookadvisorService.buscarTodos()));
        model.addAttribute("Generos", generoService.buscartodos());
        return "librosView";
    }

    @GetMapping("/borrar/{id}")
    public String Showborrar(@PathVariable Long id) {
        try {
            bookadvisorService.borrar(id);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return "redirect:/libros/?numMsg=1";
        }

        return "redirect:/libros/";
    }

    @GetMapping("/editar/{id}")
    public String getEditLibro(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("libro", bookadvisorService.buscarID(id));
            model.addAttribute("annoActual", anho);
            model.addAttribute("generoAll", generoService.buscartodos());
            return "editLibroView";
        } catch (Exception e) {
            return "redirect:/libros/?numMsg=1";
        }
    }

    @PostMapping("/editar")
    public String postEditar(@Valid Libro libro, BindingResult bindingResult, Model model,
            @RequestParam MultipartFile file) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            model.addAttribute("libro", libro);
            return "editLibroView";
        }
        if (!file.isEmpty()) {
            String newFileName = fileStorageService.store(file, libro.getId(), libro.getTitulo());
            libro.setArchivo(newFileName);
        }
        try {
            bookadvisorService.editarLibro(libro);
        } catch (Exception e) {
            return "redirect:/libros/?numMsg=1";
        }
        return "redirect:/libros/";
    }

    @GetMapping("/consulta/{id}")
    public String getOneView(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("libro", bookadvisorService.buscarID(id));
            model.addAttribute("annoActual", anho);
            return "indexOneView";
        } catch (Exception e) {
            return "redirect:/libros/?numMsg=1";
        }
    }

    @GetMapping("/nuevo")
    public String ShowNuevo(Model model) {
        try {
            model.addAttribute("libro", new Libro());
            model.addAttribute("annoActual", anho);
            model.addAttribute("generoAll", generoService.buscartodos());
            return "newLibroView";
        } catch (Exception e) {
            return "redirect:/libros/?numMsg=2";
        }
    }

    @PostMapping("/nuevo")
    public String postMethodName(@Valid Libro libro, BindingResult bindingResult, @RequestParam MultipartFile file,
            Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            model.addAttribute("libro", libro);
            return "newLibroView";
        }

        try {
            bookadvisorService.anhadirLibro(libro);
        } catch (Exception e) {

            return "redirect:/libros/?numMsg=3";
        }
        if (!file.isEmpty()) {
            String newFileName = fileStorageService.store(file, libro.getId(), libro.getTitulo());
            libro.setArchivo(newFileName);
        }
        return "redirect:/libros/";
    }

    @PostMapping("/buscar")
    public String showFindByGenero(FindForm findForm, Model model) {
        model.addAttribute("repositorio", bookadvisorService.buscar(findForm.getTitulo(), findForm.getGenero()));
        model.addAttribute("findForm", findForm);
        model.addAttribute("Generos", generoService.buscartodos());
        return "librosView";
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        System.out.println("Path: " + Paths.get("uploadDir"));
        Resource file = fileStorageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }
}