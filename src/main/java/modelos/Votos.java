package modelos;

import javax.persistence.*;

@Entity
public class Votos{

@Id
@GeneratedValue
private long id;
private String votos;

@ManyToOne(fetch = FetchType.EAGER)
private Usuario usuario;

@ManyToOne(fetch = FetchType.EAGER)
private Articulo articulo;

public Votos() {

        }

public Votos(String votos, Usuario usuario, Articulo articulo) {
        this.votos = votos;
        this.usuario = usuario;
        this.articulo = articulo;
        }

public Votos(long id, String votos, Usuario usuario, Articulo articulo) {
        this.id = id;
        this.votos = votos;
        this.usuario = usuario;
        this.articulo = articulo;
        }

public long getId() {
        return id;
        }

public void setId(long id) {
        this.id = id;
        }

public String getVotos() {
        return votos;
        }

public void setVotos(String votos) {
        this.votos = votos;
        }

public Usuario getUsuario() {
        return usuario;
        }

public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        }

public Articulo getArticulo() {
        return articulo;
        }

public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
        }
        }