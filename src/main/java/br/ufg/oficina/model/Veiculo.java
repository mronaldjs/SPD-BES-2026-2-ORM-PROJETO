package br.ufg.oficina.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10, unique = true)
    private String placa;

    @Column(length = 50)
    private String modelo;

    @Column(name = "ano_modelo")
    private Integer anoModelo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdemServico> ordens = new ArrayList<>();

    public Veiculo() {
    }

    public Veiculo(String placa, String modelo, Integer anoModelo) {
        this.placa = placa;
        this.modelo = modelo;
        this.anoModelo = anoModelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OrdemServico> getOrdens() {
        return ordens;
    }

    public void setOrdens(List<OrdemServico> ordens) {
        this.ordens = ordens;
    }

    @Override
    public String toString() {
        return "Veiculo{id=" + id + ", placa='" + placa + "', modelo='" + modelo + "'}";
    }
}
