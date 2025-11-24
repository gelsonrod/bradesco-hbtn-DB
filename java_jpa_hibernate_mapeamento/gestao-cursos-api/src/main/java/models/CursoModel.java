package models;

import entities.Curso;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CursoModel {

    public void create(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(curso);
            em.getTransaction().commit();
            System.out.println("Curso criado com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao criar curso !!! " + e.getMessage());
        } finally {
            em.close(); emf.close();
        }
    }

    public Curso findById(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close(); emf.close();
        }
    }

    public List<Curso> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        List<Curso> cursos = new ArrayList<>();
        try {
            cursos = em.createQuery("FROM Curso", Curso.class).getResultList();
            return cursos;
        } finally {
            em.close(); emf.close();
        }
    }

    public void update(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(curso);
            em.getTransaction().commit();
            System.out.println("Curso atualizado com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao atualizar curso !!! " + e.getMessage());
        } finally {
            em.close(); emf.close();
        }
    }

    public void delete(Curso curso) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestao-cursos-jpa");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Curso ref = em.find(Curso.class, curso.getId());
            if (ref != null) em.remove(ref);
            em.getTransaction().commit();
            System.out.println("Curso removido com sucesso !!!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Erro ao remover curso !!! " + e.getMessage());
        } finally {
            em.close(); emf.close();
        }
    }
}
