package br.ufg.oficina;

import br.ufg.oficina.dao.Daos;
import br.ufg.oficina.model.*;
import br.ufg.oficina.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;

/**
 * Demonstracao da camada de persistencia JPA.
 * Relacoes cobertas: 1:N (Cliente-Veiculo), 1:1 (OrdemServico-NotaFiscal), N:M (OrdemServico-Mecanico).
 */
public class Main {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Ana Souza", "123.456.789-00");
            cliente.setEndereco("Rua das Oficinas");
            cliente.setCep("74000-000");
            cliente.setNumero("100");
            cliente.setComplemento("Sala 2");

            Veiculo veiculo = new Veiculo("ABC1D23", "Gol 1.6", 2018);
            cliente.adicionarVeiculo(veiculo);
            Daos.CLIENTE.salvar(em, cliente);

            Mecanico mecanico1 = new Mecanico("Carlos Mecanico");
            Mecanico mecanico2 = new Mecanico("Diana Especialista");
            Daos.MECANICO.salvar(em, mecanico1);
            Daos.MECANICO.salvar(em, mecanico2);

            Servico trocaOleo = new Servico("Troca de oleo", 120.0);
            Servico alinhamento = new Servico("Alinhamento", 90.0);
            Daos.SERVICO.salvar(em, trocaOleo);
            Daos.SERVICO.salvar(em, alinhamento);

            Peca filtro = new Peca("Filtro de oleo", 45.0);
            Peca pastilha = new Peca("Pastilha de freio", 80.0);
            Daos.PECA.salvar(em, filtro);
            Daos.PECA.salvar(em, pastilha);

            OrdemServico ordem = new OrdemServico(LocalDate.now(), veiculo);
            ordem.adicionarMecanico(mecanico1);
            ordem.adicionarMecanico(mecanico2);
            ordem.adicionarItemServico(new ItemServico(trocaOleo, 1));
            ordem.adicionarItemServico(new ItemServico(alinhamento, 1));
            ordem.adicionarItemPeca(new ItemPeca(filtro, 1));
            ordem.adicionarItemPeca(new ItemPeca(pastilha, 2));

            NotaFiscal nota = new NotaFiscal(
                    "NF-0001",
                    "35260900000000000000550010000000011000000001",
                    LocalDate.now(),
                    ordem.getValorTotal() * 0.12);
            ordem.definirNotaFiscal(nota);

            Daos.ORDEM_SERVICO.salvar(em, ordem);
            em.getTransaction().commit();

            System.out.println("=== Persistencia concluida ===");
            System.out.println("Cliente: " + cliente);
            System.out.println("Veiculos do cliente: " + cliente.getVeiculos());
            System.out.println("Ordem: " + ordem);
            System.out.println("Mecanicos (N:M): " + ordem.getMecanicos());
            System.out.println("Nota fiscal (1:1): " + ordem.getNotaFiscal());
            System.out.println("Valor total: R$ " + ordem.getValorTotal());

            em.clear();
            OrdemServico carregada = Daos.ORDEM_SERVICO.buscarCompleta(em, ordem.getId())
                    .orElseThrow();
            System.out.println("\n=== Recarregada do banco ===");
            System.out.println(carregada);
            System.out.println("Mecanicos: " + carregada.getMecanicos());
            System.out.println("Nota: " + carregada.getNotaFiscal());

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            JPAUtil.close();
        }
    }
}
