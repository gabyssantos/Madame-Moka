package senai499.com.br.MadameMoka.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import senai499.com.br.MadameMoka.model.Cliente;
import senai499.com.br.MadameMoka.model.Pedido;
import senai499.com.br.MadameMoka.repository.ClienteRepository;
import senai499.com.br.MadameMoka.repository.PedidoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PedidosController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    // ========================
    // PÁGINA DO CARRINHO
    // ========================

    @GetMapping("/pedidos")
    public String pedidos() {
        return "pedidos/pedidos";
    }


    // ========================
    // CRIAR PEDIDO
    // ========================

    @PostMapping("/pedido/criar")
    @ResponseBody
    public String criarPedido(
            @RequestBody Pedido pedido,
            HttpSession session) {


        // ========================
        // VERIFICAR LOGIN
        // ========================

        Long clienteId =
                (Long) session.getAttribute("clienteLogado");


        if (clienteId == null) {

            return "NAO_LOGADO";

        }


        // ========================
        // BUSCAR CLIENTE
        // ========================

        Cliente cliente =
                clienteRepository.findById(clienteId)
                        .orElse(null);


        if (cliente == null) {

            return "CLIENTE_NAO_ENCONTRADO";

        }


        // ========================
        // CONFIGURAR PEDIDO
        // ========================

        pedido.setCliente(cliente);

        pedido.setData(
                LocalDateTime.now()
        );

        pedido.setStatus("PENDENTE");


        // ========================
        // PRODUTOS
        // ========================

        if (pedido.getProdutos() == null) {

            pedido.setProdutos(List.of());

        }


        // ========================
        // SALVAR
        // ========================

        pedidoRepository.save(pedido);


        return "PEDIDO_OK";

    }

}