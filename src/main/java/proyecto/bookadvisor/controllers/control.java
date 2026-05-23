package proyecto.bookadvisor.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import proyecto.bookadvisor.clases.FormInfo;
import proyecto.bookadvisor.service.BookadvisorService;
import proyecto.bookadvisor.service.EmailService;

@Controller
@RequestMapping("/public")
public class control {
    private int anho = LocalDate.now().getYear();

    @Autowired
    BookadvisorService service;

    @Autowired
    EmailService emailService;


    @GetMapping("/")
    public String ShowHome(Model model) {
        model.addAttribute("annoActual", anho);
        return "indexView";
    }

    @GetMapping("/whoit")
    public String ShowWhoit(Model model) {
        model.addAttribute("annoActual", anho);
        return "WhoitView";
    }

    @GetMapping("/contact")
    public String getMethodName(FormInfo formInfo, Model model) {
        model.addAttribute("formInfo", new FormInfo());
        model.addAttribute("annoActual", anho);
        return "contactView";
    }

    @PostMapping("/contac")
    public String postMethodName(@Valid FormInfo formInfo, BindingResult bindingResult, Model model) {
        model.addAttribute("annoActual", anho);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors());
            model.addAttribute("formInfo", formInfo);
            return "contactView";
        }
        service.guardarQueja(formInfo);
        String cuerpoMensaje = formInfo.toString();
        String destinatario = "alejandro.blanco.cuesta@gmail.com";
        String asunto = "BookAdvisor - Contacto Web";
        /* boolean envioEmail = */ emailService.sendEmail(destinatario, asunto, cuerpoMensaje);
        model.addAttribute("formInfo", formInfo);
        /* control de creciomiento de arreglo del servicio */
        System.out.println(service.cargarDatos().size());
        return "contactView";
    }

}