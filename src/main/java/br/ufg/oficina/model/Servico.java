package br.ufg.oficina.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(nullable = false)
    private Double valor;

    @OneToMany(mappedBy = "servico")
    private List<ItemServico> itens = new ArrayList<>();

    public Servico() {
    }

    public Servico(String descricao, Double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<ItemServico> getItens() {
        return itens;
    }

    public void setItens(List<ItemServico> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Servico{id=" + id + ", descricao='" + descricao + "', valor=" + valor + "}";
    }
}
