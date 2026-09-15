package br.ufg.oficina.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String numero;

    @Column(name = "chave_acesso", length = 44)
    private String chaveAcesso;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "valor_imposto")
    private Double valorImposto;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ordem_id", nullable = false, unique = true)
    private OrdemServico ordemServico;

    public NotaFiscal() {
    }

    public NotaFiscal(String numero, String chaveAcesso, LocalDate dataEmissao, Double valorImposto) {
        this.numero = numero;
        this.chaveAcesso = chaveAcesso;
        this.dataEmissao = dataEmissao;
        this.valorImposto = valorImposto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Double getValorImposto() {
        return valorImposto;
    }

    public void setValorImposto(Double valorImposto) {
        this.valorImposto = valorImposto;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    @Override
    public String toString() {
        return "NotaFiscal{id=" + id + ", numero='" + numero + "', valorImposto=" + valorImposto + "}";
    }
}
