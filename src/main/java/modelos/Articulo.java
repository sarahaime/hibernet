package modelos;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Articulo {
    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "text")
    private String cuerpo;
    private String titulo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    private Usuario autor;

    //para que incluya los comentarios sin problemas
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comentario> comentarios;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Etiqueta> etiquetas;

    public Articulo() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public void agregarEtiqueta(Etiqueta e){
        etiquetas.add(e);
    }
}
