package entities;

import javax.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)
    private String nome;

    @Column(nullable=false)
    private Integer quantidade;

    @Column(nullable=false)
    private Double preco;

    @Column(nullable=false)
    private Boolean status;

    public Produto() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    @Override
    public String toString() {
        return "Produto{id=" + id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", preco=" + preco +
                ", status=" + status +
                '}';
    }
}
