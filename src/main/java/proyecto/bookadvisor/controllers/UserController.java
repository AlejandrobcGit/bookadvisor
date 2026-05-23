package proyecto.bookadvisor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proyecto.bookadvisor.domain.Usuario;
import proyecto.bookadvisor.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    String errorMsg;

    @GetMapping("/")
    public String ShowAllUser(Model model, @RequestParam(defaultValue = "0", required = false) int error) {
        model.addAttribute("listaUsers", userService.obtenerTodos());
        if (error == 1) {
            model.addAttribute("error", errorMsg);
        }
        return "/user/listAllView";
    }

    @GetMapping("/Borrar/{id}")
    public String removeUser(@PathVariable Long id, Model model) {
        userService.borrar(id);
        return "redirect:/user/";
    }

    @GetMapping("/Editar/{id}")
    public String ShowEditUser(@PathVariable Long id, Model model) {
        System.out.println("id: " + id);
        model.addAttribute("usuario", userService.obtenerID(id));
        return "/user/editUserView";
    }

    @PostMapping("/Editar")
    public String postEditarUser(Usuario usuario) {
        try {
            userService.modificar(usuario);
            return "redirect:/user/";
        } catch (Exception e) {
            errorMsg = e.getMessage();
            return "redirect:/user/?error=1";
        }
    }

    @GetMapping("/New")
    public String ShowNewUser(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "/user/newUserView";
    }

    @PostMapping("/New")
    public String postNewUser(Usuario usuario) {

        try {
            userService.añadir(usuario);
            return "redirect:/user/";
        } catch (Exception e) {
            errorMsg = e.getMessage();
            return "redirect:/user/?error=1";
        }
    }

    @GetMapping("/auto")
    public String ShowautoNewUser(Model model, @RequestParam(defaultValue = "0", required = false) int error) {
        model.addAttribute("usuario", new Usuario());
        System.out.println("Error : " + error);
        if (error == 1) {
            model.addAttribute("error", errorMsg);
        }
        return "user/autoNewUserView";
    }

    @PostMapping("/auto")
    public String postautoNewUser(Usuario usuario) {
        try {
            userService.añadir(usuario);
            return "redirect:/login";
        } catch (Exception e) {
            errorMsg = e.getMessage();
            return "redirect:/autoNewUser?error=1"; 
        }
    }
}
