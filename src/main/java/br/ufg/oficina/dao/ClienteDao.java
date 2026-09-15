package br.ufg.oficina.dao;

import br.ufg.oficina.model.Cliente;
import jakarta.persistence.EntityManager;
import java.util.Optional;

public class ClienteDao extends GenericDao<Cliente> {

    public ClienteDao() {
        super(Cliente.class);
    }

    public Optional<Cliente> buscarPorCpfCnpj(EntityManager em, String cpfCnpj) {
        return em.createQuery(
                        "SELECT c FROM Cliente c WHERE c.cpfCnpj = :cpfCnpj", Cliente.class)
                .setParameter("cpfCnpj", cpfCnpj)
                .getResultStream()
                .findFirst();
    }

    public Optional<Cliente> buscarComVeiculos(EntityManager em, Long id) {
        return em.createQuery(
                        "SELECT DISTINCT c FROM Cliente c LEFT JOIN FETCH c.veiculos WHERE c.id = :id",
                        Cliente.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }
}
