package entities;

import javax.persistence.*;

@Entity
@Table(name="materiais_curso")
public class MaterialCurso {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=255)
    private String url;

    @OneToOne(mappedBy = "material")
    private Curso curso; // opcional (lado inverso)

    public Long getId() { return id; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Curso getCurso() { return curso; }

    @Override public String toString() {
        return "MaterialCurso{id=" + id + ", url='" + url + "'}";
    }
}
