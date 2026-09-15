package br.ufg.oficina.dao;

import br.ufg.oficina.model.Mecanico;
import br.ufg.oficina.model.Peca;
import br.ufg.oficina.model.Servico;
import br.ufg.oficina.model.Veiculo;

public final class Daos {

    public static final ClienteDao CLIENTE = new ClienteDao();
    public static final GenericDao<Veiculo> VEICULO = new GenericDao<>(Veiculo.class);
    public static final OrdemServicoDao ORDEM_SERVICO = new OrdemServicoDao();
    public static final GenericDao<Mecanico> MECANICO = new GenericDao<>(Mecanico.class);
    public static final GenericDao<Servico> SERVICO = new GenericDao<>(Servico.class);
    public static final GenericDao<Peca> PECA = new GenericDao<>(Peca.class);

    private Daos() {
    }
}
