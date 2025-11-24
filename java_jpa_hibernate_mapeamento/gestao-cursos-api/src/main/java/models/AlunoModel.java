package models;

import entities.Aluno;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoModel {

    public void create(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Iniciando a transação");
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno criado com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao criar um aluno !!! " + e.getMessage());
        } finally {
            em.close();
            emf.close();
            System.out.println("Finalizando a transação");
        }
    }

    public Aluno findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Aluno.class, id);
        } finally {
            em.close(); emf.close();
        }
    }

    public List<Aluno> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Aluno> result = new ArrayList<>();
        try {
            result = em.createQuery("FROM Aluno", Aluno.class).getResultList();
            return result;
        } finally {
            em.close(); emf.close();
        }
    }

    public void update(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(aluno);
            em.getTransaction().commit();
            System.out.println("Aluno atualizado com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao atualizar aluno !!! " + e.getMessage());
        } finally {
            em.close(); emf.close();
        }
    }

    public void delete(Aluno aluno) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Aluno ref = em.find(Aluno.class, aluno.getId());
            if (ref != null) em.remove(ref);
            em.getTransaction().commit();
            System.out.println("Aluno removido com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao remover aluno !!! " + e.getMessage());
        } finally {
            em.close(); emf.close();
        }
    }
}
