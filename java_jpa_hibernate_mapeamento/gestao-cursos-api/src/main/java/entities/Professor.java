package entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="professores")
public class Professor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome_completo", nullable=false, length=150)
    private String nomeCompleto;

    @Column(nullable=false, unique=true, length=40)
    private String matricula;

    @Column(nullable=false, unique=true, length=120)
    private String email;

    // 1 Professor -> 0..* Curso
    @OneToMany(mappedBy="professor")
    private List<Curso> cursos = new ArrayList<>();

    public Long getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Curso> getCursos() { return cursos; }

    @Override public String toString() {
        return "Professor{id=" + id + ", nomeCompleto='" + nomeCompleto + "', matricula='" + matricula + "', email='" + email + "'}";
    }
}
