package entities;

import javax.persistence.*;

@Entity
@Table(name="telefones")
public class Telefone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=3)
    private String DDD;

    @Column(nullable=false, length=20)
    private String numero;

    @ManyToOne(optional=false)
    @JoinColumn(name="aluno_id", nullable=false)
    private Aluno aluno;

    public Long getId() { return id; }
    public String getDDD() { return DDD; }
    public void setDDD(String DDD) { this.DDD = DDD; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    @Override public String toString() {
        return "Telefone{id=" + id + ", DDD='" + DDD + "', numero='" + numero + "'}";
    }
}
