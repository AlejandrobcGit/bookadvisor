package proyecto.bookadvisor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/signin")
    public String showLogin() {
        return "/sing/signinView";
    }

    @GetMapping("/signout")
    public String showLogout() {
        return "/sing/sigoutView";
    }

    @GetMapping("/errorAccess")
    public String showerror() {
        return "/sing/errorAccess";
    }

    @GetMapping("/")
    public String redirecttopublic() {
        return "redirect:/public/";
    }
}
