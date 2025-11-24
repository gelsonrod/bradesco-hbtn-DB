package entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nome_completo", nullable=false, length=150)
    private String nomeCompleto;

    @Column(nullable=false, unique=true, length=40)
    private String matricula;

    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private Date nascimento;

    @Column(nullable=false, unique=true, length=120)
    private String email;

    // 1 Aluno -> 0..* Endereco
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    // 1 Aluno -> 0..* Telefone
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones = new ArrayList<>();

    // N:N com Curso
    @ManyToMany(mappedBy = "alunos")
    private Set<Curso> cursos = new HashSet<>();

    public Long getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public Date getNascimento() { return nascimento; }
    public void setNascimento(Date nascimento) { this.nascimento = nascimento; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Endereco> getEnderecos() { return enderecos; }
    public List<Telefone> getTelefones() { return telefones; }
    public Set<Curso> getCursos() { return cursos; }

    public void addEndereco(Endereco e) { e.setAluno(this); enderecos.add(e); }
    public void addTelefone(Telefone t) { t.setAluno(this); telefones.add(t); }

    @Override public String toString() {
        return "Aluno{id=" + id + ", nomeCompleto='" + nomeCompleto + "', matricula='" + matricula + "', email='" + email + "'}";
    }
}
