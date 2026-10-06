package senai499.com.br.MadameMoka.controller;

import senai499.com.br.MadameMoka.model.Administrador;
import senai499.com.br.MadameMoka.model.Pedido;
import senai499.com.br.MadameMoka.repository.AdministradorRepository;
import senai499.com.br.MadameMoka.repository.ClienteRepository;
import senai499.com.br.MadameMoka.repository.PedidoRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;


    // ================= LOGIN =================

    @PostMapping("/login")
    @ResponseBody
    public String login(
            @RequestBody Administrador administrador,
            HttpSession session) {

        Administrador administradorEncontrado =
                administradorRepository.findByEmail(administrador.getEmail())
                        .orElse(null);

        if (administradorEncontrado == null) {
            return "LOGIN_INCORRETO";
        }

        if (!administradorEncontrado.getSenha()
                .equals(administrador.getSenha())) {
            return "LOGIN_INCORRETO";
        }

        session.setAttribute(
                "administradorLogado",
                administradorEncontrado.getId()
        );

        return "LOGIN_OK";
    }


    // ================= VERIFICAR LOGIN =================

    @GetMapping("/verificar")
    @ResponseBody
    public String verificarLogin(HttpSession session) {

        if (session.getAttribute("administradorLogado") == null) {
            return "NAO_LOGADO";
        }

        return "LOGADO";
    }


    // ================= SAIR =================

    @PostMapping("/sair")
    @ResponseBody
    public String sair(HttpSession session) {

        session.removeAttribute("administradorLogado");

        return "LOGOUT_OK";
    }


    // ================= QUANTIDADE DE CLIENTES =================

    @GetMapping("/clientes/quantidade")
    @ResponseBody
    public long quantidadeClientes() {

        return clienteRepository.count();
    }


    // ================= QUANTIDADE DE PEDIDOS =================

    @GetMapping("/pedidos/quantidade")
    @ResponseBody
    public long quantidadePedidos() {

        return pedidoRepository.count();
    }


    // ================= PEDIDOS PENDENTES =================

    @GetMapping("/pedidos/pendentes")
    @ResponseBody
    public long pedidosPendentes() {

        return pedidoRepository.countByStatus("PENDENTE");
    }

    // ================= LISTAR PEDIDOS =================

    @GetMapping("/pedidos/listar")
    @ResponseBody
    public java.util.List<senai499.com.br.MadameMoka.model.Pedido> listarPedidos(
            HttpSession session) {

        if (session.getAttribute("administradorLogado") == null) {
            return java.util.Collections.emptyList();
        }

        return pedidoRepository.findAll();
    }

    // ================= ABRIR GERENCIAMENTO DE PEDIDOS =================

    @GetMapping("/pedidos")
    public String gerenciarPedidos(HttpSession session) {

        if (session.getAttribute("administradorLogado") == null) {
            return "redirect:/login-admin";
        }

        return "gerenciar-pedidos";
    }
    // ================= ALTERAR STATUS DO PEDIDO =================

    @PostMapping("/pedidos/status")
    @ResponseBody
    public String alterarStatusPedido(
            @RequestParam Long id,
            @RequestParam String status,
            HttpSession session) {

        if (session.getAttribute("administradorLogado") == null) {
            return "NAO_LOGADO";
        }

        Pedido pedido =
                pedidoRepository.findById(id).orElse(null);

        if (pedido == null) {
            return "PEDIDO_NAO_ENCONTRADO";
        }

        pedido.setStatus(status);

        pedidoRepository.save(pedido);

        return "STATUS_ATUALIZADO";
    }
}