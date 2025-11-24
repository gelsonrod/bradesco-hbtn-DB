package models;

import entities.Pessoa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaModel {

    public void create(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println("Iniciando a transação (Pessoa.create)");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            System.out.println("Pessoa criada com sucesso!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao criar a pessoa: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    public void update(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            System.out.println("Pessoa atualizada com sucesso!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao atualizar a pessoa: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    public void delete(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Pessoa ref = em.find(Pessoa.class, p.getId());
            if (ref != null) em.remove(ref);
            em.getTransaction().commit();
            System.out.println("Pessoa removida com sucesso!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao remover a pessoa: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    public Pessoa findById(Pessoa p) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Pessoa.class, p.getId());
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Pessoa> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("admin-jpa");
        EntityManager em = emf.createEntityManager();
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            pessoas = em.createQuery("FROM Pessoa", Pessoa.class).getResultList();
            return pessoas;
        } finally {
            em.close();
            emf.close();
        }
    }
}
