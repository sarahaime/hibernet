package modelos;

import servicios.LikeArticuloServices;

import javax.persistence.*;

import java.util.Set;

@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "text")
    private String comentario;

    @OneToOne(fetch = FetchType.EAGER)
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Articulo articulo;

    @Transient
    private int likesCount;

    @Transient
    private int dislikesCount;

    //todo: cambiar aqui para que traiga la cuenta de likes
    public int getLikesCount() {
        return likesCount;
    }


    //todo: cambiar aqui para que traiga la cuenta de no likes
    public int getDislikesCount() {

        return 5;
    }





    public Comentario(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }


}
