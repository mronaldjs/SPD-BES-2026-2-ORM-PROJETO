package br.ufg.oficina.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "peca")
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "preco_unitario", nullable = false)
    private Double precoUnitario;

    @OneToMany(mappedBy = "peca")
    private List<ItemPeca> itens = new ArrayList<>();

    public Peca() {
    }

    public Peca(String nome, Double precoUnitario) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public List<ItemPeca> getItens() {
        return itens;
    }

    public void setItens(List<ItemPeca> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Peca{id=" + id + ", nome='" + nome + "', precoUnitario=" + precoUnitario + "}";
    }
}
