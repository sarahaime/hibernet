package modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;


@Entity
@Access(AccessType.FIELD)
public class Valoracion {
    @Id
    @GeneratedValue
    private Long id;

    private boolean tipo;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( unique = true, updatable = false, nullable = false)
    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( unique = true, updatable = false, nullable = false)
    private Comentario comentario;

    public Valoracion() {
    }

    public Valoracion(boolean tipo, Usuario usuario, Comentario comentario) {
        this.tipo = tipo;
        this.usuario = usuario;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
}
