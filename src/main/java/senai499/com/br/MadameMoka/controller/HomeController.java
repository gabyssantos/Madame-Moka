package senai499.com.br.MadameMoka.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }


    @GetMapping("/perfil")
    public String perfil() {
        return "perfil";
    }


    @GetMapping("/admin")
    public String admin(HttpSession session) {

        if (session.getAttribute("administradorLogado") == null) {

            return "redirect:/login-admin";
        }

        return "admin";
    }


    @GetMapping("/login-admin")
    public String loginAdmin() {
        return "login-admin";
    }
}