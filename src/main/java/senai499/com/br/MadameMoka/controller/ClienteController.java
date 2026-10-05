package senai499.com.br.MadameMoka.controller;

import senai499.com.br.MadameMoka.model.Cliente;
import senai499.com.br.MadameMoka.repository.ClienteRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;


    // ================= CADASTRO =================

    @PostMapping("/cadastrar")
    @ResponseBody
    public String cadastrar(
            @RequestBody Cliente cliente,
            HttpSession session) {

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            return "EMAIL_EXISTENTE";
        }

        Cliente clienteSalvo = clienteRepository.save(cliente);

        // Guarda o cliente na sessão
        session.setAttribute("clienteLogado", clienteSalvo.getId());

        return "CADASTRO_OK";
    }


    // ================= LOGIN =================

    @PostMapping("/login")
    @ResponseBody
    public String login(
            @RequestBody Cliente cliente,
            HttpSession session) {

        Cliente clienteEncontrado =
                clienteRepository.findByEmail(cliente.getEmail())
                        .orElse(null);

        if (clienteEncontrado == null) {
            return "NAO_CADASTRADO";
        }

        if (!clienteEncontrado.getSenha().equals(cliente.getSenha())) {
            return "SENHA_INCORRETA";
        }

        // Guarda o cliente logado na sessão
        session.setAttribute(
                "clienteLogado",
                clienteEncontrado.getId()
        );

        return "LOGIN_OK";
    }


    // ================= LOGOUT =================

    @PostMapping("/sair")
    @ResponseBody
    public String sair(HttpSession session) {

        session.invalidate();

        return "LOGOUT_OK";
    }

    // ================= VERIFICAR LOGIN =================

    @GetMapping("/verificar")
    @ResponseBody
    public String verificarLogin(HttpSession session) {

        if (session.getAttribute("clienteLogado") == null) {
            return "NAO_LOGADO";
        }

        return "LOGADO";
    }
    // ================= PERFIL =================

    @GetMapping("/perfil")
    @ResponseBody
    public Cliente perfil(HttpSession session) {

        Long clienteId =
                (Long) session.getAttribute("clienteLogado");

        if (clienteId == null) {
            return null;
        }

        return clienteRepository.findById(clienteId)
                .orElse(null);
    }
}