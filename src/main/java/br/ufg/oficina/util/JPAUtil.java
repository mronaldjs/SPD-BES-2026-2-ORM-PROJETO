package br.ufg.oficina.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAUtil {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("oficinaPU");

    private JPAUtil() {
    }

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }

    public static void close() {
        if (EMF.isOpen()) {
            EMF.close();
        }
    }
}
