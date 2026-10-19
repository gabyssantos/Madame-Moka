package senai499.com.br.MadameMoka.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ItemPedido {

    private String nome;

    private double preco;

    private int quantidade;

    public ItemPedido() {
    }

    public ItemPedido(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}