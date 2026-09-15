package br.ufg.oficina.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "valor_total")
    private Double valorTotal;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToMany
    @JoinTable(
            name = "ordem_mecanico",
            joinColumns = @JoinColumn(name = "ordem_id"),
            inverseJoinColumns = @JoinColumn(name = "mecanico_id")
    )
    private List<Mecanico> mecanicos = new ArrayList<>();

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemServico> itensServico = new ArrayList<>();

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPeca> itensPeca = new ArrayList<>();

    @OneToOne(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private NotaFiscal notaFiscal;

    public OrdemServico() {
    }

    public OrdemServico(LocalDate dataEmissao, Veiculo veiculo) {
        this.dataEmissao = dataEmissao;
        this.veiculo = veiculo;
        this.valorTotal = 0.0;
    }

    public void adicionarMecanico(Mecanico mecanico) {
        mecanicos.add(mecanico);
        mecanico.getOrdens().add(this);
    }

    public void adicionarItemServico(ItemServico item) {
        itensServico.add(item);
        item.setOrdemServico(this);
        recalcularValorTotal();
    }

    public void adicionarItemPeca(ItemPeca item) {
        itensPeca.add(item);
        item.setOrdemServico(this);
        recalcularValorTotal();
    }

    public void definirNotaFiscal(NotaFiscal nota) {
        this.notaFiscal = nota;
        nota.setOrdemServico(this);
    }

    public void recalcularValorTotal() {
        double totalServicos = itensServico.stream()
                .mapToDouble(i -> i.getServico().getValor() * i.getQuantidade())
                .sum();
        double totalPecas = itensPeca.stream()
                .mapToDouble(i -> i.getPeca().getPrecoUnitario() * i.getQuantidade())
                .sum();
        this.valorTotal = totalServicos + totalPecas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void setMecanicos(List<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    public List<ItemServico> getItensServico() {
        return itensServico;
    }

    public void setItensServico(List<ItemServico> itensServico) {
        this.itensServico = itensServico;
    }

    public List<ItemPeca> getItensPeca() {
        return itensPeca;
    }

    public void setItensPeca(List<ItemPeca> itensPeca) {
        this.itensPeca = itensPeca;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    @Override
    public String toString() {
        return "OrdemServico{id=" + id + ", dataEmissao=" + dataEmissao + ", valorTotal=" + valorTotal + "}";
    }
}
