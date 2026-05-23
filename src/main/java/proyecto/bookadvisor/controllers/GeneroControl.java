package proyecto.bookadvisor.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proyecto.bookadvisor.domain.Genero;
import proyecto.bookadvisor.service.GeneroService;

@Controller
@RequestMapping("/genero")
public class GeneroControl {
    private int anho = LocalDate.now().getYear();

    @Autowired
    private GeneroService generoService;

    @GetMapping("/")
    public String ShowHome(Model model) {
        model.addAttribute("annoActual", anho);
        model.addAttribute("repositorio", generoService.buscartodos());
        return "genero/generoView";
    }

    @GetMapping ("borrar/{id}")
    public String ShowDelete(Model model, @PathVariable Long id) {
        try {
            generoService.borrar(id);
        } catch (Exception e) {
            System.out.println("Error al borrar genero: " + e.getMessage());
        }        
        return "redirect:/genero/";
    }

    @GetMapping ("editar/{id}")
    public String ShowEditFrom(Model model, @PathVariable Long id) {
        model.addAttribute("annoActual", anho);
        model.addAttribute("genero", generoService.buscarPorId(id));
        return "genero/editGeneroView";
    }

    @PostMapping("/editar")
    public String postEditFrom(Genero genero) {
        generoService.anhdir(genero);
        return "redirect:/genero/";
    }
    @GetMapping ("/nuevo")
    public String ShowNewFrom(Model model) {
        model.addAttribute("annoActual", anho);
        model.addAttribute("genero", new Genero());
        return "genero/newGeneroView";
    }

    //no hago un postmapping para nuevo por que uso el mismo de editar 
}
