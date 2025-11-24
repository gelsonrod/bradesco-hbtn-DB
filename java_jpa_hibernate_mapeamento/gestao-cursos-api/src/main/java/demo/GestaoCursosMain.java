package demo;

import entities.*;
import models.AlunoModel;
import models.CursoModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GestaoCursosMain {

    private static Date data(int ano, int mes, int dia) {
        Calendar c = Calendar.getInstance();
        c.set(ano, mes-1, dia, 0,0,0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static void main(String[] args) {
        AlunoModel alunoModel = new AlunoModel();
        CursoModel cursoModel = new CursoModel();

        // Aluno + 1 Telefone + 1 Endereco
        Aluno aluno = new Aluno();
        aluno.setNomeCompleto("Jane Doe");
        aluno.setMatricula("A123");
        aluno.setNascimento(data(1998, 7, 14));
        aluno.setEmail("jane@example.com");

        Telefone tel = new Telefone();
        tel.setDDD("11");
        tel.setNumero("90000-0000");
        aluno.addTelefone(tel);

        Endereco end = new Endereco();
        end.setLogradouro("Rua das Palmeiras");
        end.setEndereco("Apto 12, Bloco B");
        end.setNumero("123");
        end.setBairro("Centro");
        end.setCidade("São Paulo");
        end.setEstado("SP");
        end.setCep(1000000);
        aluno.addEndereco(end);

        alunoModel.create(aluno);

        // Professor
        Professor prof = new Professor();
        prof.setNomeCompleto("Dr. Xavier");
        prof.setMatricula("P001");
        prof.setEmail("xavier@school.edu");

        // Material
        MaterialCurso mat = new MaterialCurso();
        mat.setUrl("https://cdn.exemplo.com/material/java.pdf");

        // Curso com professor + material + aluno
        Curso curso = new Curso();
        curso.setNome("Introdução a Java");
        curso.setSigla("IJAVA");
        curso.setProfessor(prof);
        curso.setMaterial(mat);
        curso.addAluno(aluno);

        cursoModel.create(curso);

        // Consultas rápidas
        List<Aluno> alunos = alunoModel.findAll();
        System.out.println("Alunos: " + alunos.size());

        List<Curso> cursos = cursoModel.findAll();
        System.out.println("Cursos: " + cursos.size());
    }
}
