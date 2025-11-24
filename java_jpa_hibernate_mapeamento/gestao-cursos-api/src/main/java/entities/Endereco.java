package entities;

import javax.persistence.*;

@Entity
@Table(name="enderecos")
public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=150)
    private String logradouro;

    @Column(nullable=false, length=150)
    private String endereco;  // conforme diagrama

    @Column(nullable=false, length=10)
    private String numero;

    @Column(nullable=false, length=80)
    private String bairro;

    @Column(nullable=false, length=80)
    private String cidade;

    @Column(nullable=false, length=2)
    private String estado;

    @Column(nullable=false)
    private Integer cep;

    @ManyToOne(optional=false)
    @JoinColumn(name="aluno_id", nullable=false)
    private Aluno aluno;

    public Long getId() { return id; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Integer getCep() { return cep; }
    public void setCep(Integer cep) { this.cep = cep; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    @Override public String toString() {
        return "Endereco{id=" + id + ", " + logradouro + ", " + endereco + ", nº " + numero + ", " +
                bairro + ", " + cidade + "/" + estado + ", CEP=" + cep + "}";
    }
}
