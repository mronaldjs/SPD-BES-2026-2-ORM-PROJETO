package br.ufg.oficina.dao;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class GenericDao<T> {

    private final Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T salvar(EntityManager em, T entity) {
        em.persist(entity);
        return entity;
    }

    public T atualizar(EntityManager em, T entity) {
        return em.merge(entity);
    }

    public Optional<T> buscarPorId(EntityManager em, Long id) {
        return Optional.ofNullable(em.find(entityClass, id));
    }

    public List<T> listarTodos(EntityManager em) {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    public void remover(EntityManager em, T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }
}
