package proyecto.bookadvisor.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proyecto.bookadvisor.domain.Libro;
import proyecto.bookadvisor.domain.Valoracion;
import proyecto.bookadvisor.service.BookadvisorService;
import proyecto.bookadvisor.service.ValoracionService;

@Controller
@RequestMapping("/valoracion")
public class ValoracionControl {
    private int anho = LocalDate.now().getYear();

    @Autowired
    private ValoracionService valoracionService;
    @Autowired
    private BookadvisorService bookadvisorService;

    String errorMsg; 

    @GetMapping("/")
    public String ShowHome(Model model, @RequestParam long id,@RequestParam (required = false, defaultValue = "0") int error) {
        Libro libroactual = bookadvisorService.buscarID(id);
        model.addAttribute("annoActual", anho);
        model.addAttribute("libro", libroactual);
        model.addAttribute("listaValoraciones", valoracionService.buscarPorLibroId(id));
        if (error == 1) {
            model.addAttribute("error", errorMsg);
        }
        return "valoraciones/valoracionView";
    }

    @GetMapping("/nuevo/{id}")
    public String ShowNewFrom(Model model, @PathVariable Long id) {
        model.addAttribute("annoActual", anho);
        Valoracion newValoracion = new Valoracion();
        newValoracion.setLibro(bookadvisorService.buscarID(id));
        model.addAttribute("valoracion", newValoracion);
        return "valoraciones/newValoracionView";
    }

    @PostMapping("/nuevo")
    public String postNewFrom(Valoracion valoracion, @RequestParam long idlibro) {
        valoracion.setLibro(bookadvisorService.buscarID(idlibro));
        try {
            valoracionService.anhdir(valoracion);
            return "redirect:/valoracion/?id=" + idlibro;
        } catch (Exception e) {
            errorMsg = e.getMessage();
            return "redirect:/valoracion/?id=" + idlibro + "&error=" + "1" ;
        }
    }

    @GetMapping("/borrar/{id}/{idlibro}")
    public String showBorrar (@PathVariable long id, @PathVariable long idlibro){
        try {
            valoracionService.borrar(id);
            return "redirect:/valoracion/?id=" + idlibro;
        } catch (Exception e) {
            errorMsg = e.getMessage();
            System.out.println("error: " + errorMsg);
            return "redirect:/valoracion/?id=" + idlibro + "&error=" + "1" ;
        }
       
    }
}
