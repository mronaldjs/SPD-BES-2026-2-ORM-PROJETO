package br.ufg.oficina.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_servico")
public class ItemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ordem_id", nullable = false)
    private OrdemServico ordemServico;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    public ItemServico() {
    }

    public ItemServico(Servico servico, Integer quantidade) {
        this.servico = servico;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "ItemServico{id=" + id + ", quantidade=" + quantidade + ", servico=" + servico + "}";
    }
}
