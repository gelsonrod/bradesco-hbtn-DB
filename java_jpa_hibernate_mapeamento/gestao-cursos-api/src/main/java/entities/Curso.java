package entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="cursos")
public class Curso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)
    private String nome;

    @Column(nullable=false, unique=true, length=20)
    private String sigla;

    // Muitos cursos para um professor
    @ManyToOne(optional=false, cascade = CascadeType.PERSIST)
    @JoinColumn(name="professor_id", nullable=false)
    private Professor professor;

    // Um curso -> 0..1 material (exclusivo)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="material_id", unique=true)
    private MaterialCurso material;

    // Curso 0..* <-> 1..* Aluno  (N:N)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="curso_aluno",
            joinColumns = @JoinColumn(name="curso_id"),
            inverseJoinColumns = @JoinColumn(name="aluno_id"))
    private Set<Aluno> alunos = new HashSet<>();

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public MaterialCurso getMaterial() { return material; }
    public void setMaterial(MaterialCurso material) { this.material = material; }
    public Set<Aluno> getAlunos() { return alunos; }
    public void addAluno(Aluno a) { alunos.add(a); a.getCursos().add(this); }

    @Override public String toString() {
        return "Curso{id=" + id + ", nome='" + nome + "', sigla='" + sigla + "'}";
    }
}
