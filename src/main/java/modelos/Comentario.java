package modelos;

import javax.persistence.*;
import java.util.Set;
import java.util.List;

@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private long id;
    private String comentario;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Articulo articulo;

    @OneToMany(mappedBy = "comentario", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Valoracion> valoraciones;

    @Transient
    private List<Valoracion> meGusta;

    @Transient
    private List<Valoracion> meDisgusta;


    public Comentario() {

    }

    public Comentario(String comentario, Usuario autor, Articulo articulo, Set<Valoracion> valoraciones) {
        this.comentario = comentario;
        this.autor = autor;
        this.articulo = articulo;
        this.valoraciones = valoraciones;
    }
        public long getCantidadMeGusta() {
            return this.meGusta == null ? 0 : this.meGusta.size();
        }

        public long getCantidadMeDisgusta () {
            return this.meDisgusta == null ? 0 : this.meDisgusta.size();
        }

        public List<Valoracion> getMeGusta () {
            return meGusta;
        }

        public void setMeGusta (List < Valoracion > meGusta) {
            this.meGusta = meGusta;
        }

        public List<Valoracion> getMeDisgusta () {
            return meDisgusta;
        }

        public void setMeDisgusta (List < Valoracion > meDisgusta) {
            this.meDisgusta = meDisgusta;
        }

        public Set<Valoracion> getValoraciones () {
            return valoraciones;
        }

        public void setValoraciones (Set < Valoracion > valoraciones) {
            this.valoraciones = valoraciones;
        }


        public long getId () {
            return id;
        }

        public void setId ( long id){
            this.id = id;
        }

        public String getComentario () {
            return comentario;
        }

        public void setComentario (String comentario){
            this.comentario = comentario;
        }

        public Usuario getAutor () {
            return autor;
        }

        public void setAutor (Usuario autor){
            this.autor = autor;
        }

        public Articulo getArticulo () {
            return articulo;
        }

        public void setArticulo (Articulo articulo){
            this.articulo = articulo;
        }

    }