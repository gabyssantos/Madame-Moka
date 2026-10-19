package senai499.com.br.MadameMoka.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    private String status;

    private double valorTotal;


    // ========================
    // ENDEREÇO
    // ========================

    private String cep;

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;


    // ========================
    // PAGAMENTO E ENTREGA
    // ========================

    private String formaPagamento;

    private String tipoEntrega;


    // ========================
    // CLIENTE
    // ========================

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    // ========================
    // PRODUTOS
    // ========================

    @ElementCollection
    @CollectionTable(
            name = "pedido_produtos",
            joinColumns = @JoinColumn(name = "pedido_id")
    )
    private List<ItemPedido> produtos;


    // ========================
    // CONSTRUTOR
    // ========================

    public Pedido() {
    }


    // ========================
    // GETTERS E SETTERS
    // ========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }


    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }


    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }


    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public List<ItemPedido> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ItemPedido> produtos) {
        this.produtos = produtos;
    }

}