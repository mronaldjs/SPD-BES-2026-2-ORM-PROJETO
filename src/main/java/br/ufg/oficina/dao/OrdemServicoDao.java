package br.ufg.oficina.dao;

import br.ufg.oficina.model.OrdemServico;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class OrdemServicoDao extends GenericDao<OrdemServico> {

    public OrdemServicoDao() {
        super(OrdemServico.class);
    }

    public Optional<OrdemServico> buscarCompleta(EntityManager em, Long id) {
        Optional<OrdemServico> ordem = em.createQuery(
                        """
                        SELECT DISTINCT o FROM OrdemServico o
                        LEFT JOIN FETCH o.mecanicos
                        LEFT JOIN FETCH o.notaFiscal
                        WHERE o.id = :id
                        """,
                        OrdemServico.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();

        // Evita MultipleBagFetchException: bags adicionais em consultas separadas
        ordem.ifPresent(o -> {
            em.createQuery(
                            """
                            SELECT DISTINCT o FROM OrdemServico o
                            LEFT JOIN FETCH o.itensServico
                            WHERE o.id = :id
                            """,
                            OrdemServico.class)
                    .setParameter("id", id)
                    .getResultList();
            em.createQuery(
                            """
                            SELECT DISTINCT o FROM OrdemServico o
                            LEFT JOIN FETCH o.itensPeca
                            WHERE o.id = :id
                            """,
                            OrdemServico.class)
                    .setParameter("id", id)
                    .getResultList();
        });

        return ordem;
    }

    public List<OrdemServico> listarPorPlaca(EntityManager em, String placa) {
        return em.createQuery(
                        """
                        SELECT o FROM OrdemServico o
                        JOIN o.veiculo v
                        WHERE v.placa = :placa
                        """,
                        OrdemServico.class)
                .setParameter("placa", placa)
                .getResultList();
    }
}
