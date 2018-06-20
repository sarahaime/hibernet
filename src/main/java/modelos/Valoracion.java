package modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


    @Entity
    @Table(name = "valoracion")
    @Access(AccessType.FIELD)
    public class Valoracion {
        @Id
        @GeneratedValue
        @Column(name = "id")
        private Long id;
        @Column(name = "tipo")
        private boolean tipo;
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinColumn(name = "usuarioId", unique = true, updatable = false, nullable = false)
        private Usuario usuario;
        @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinColumn(name = "articuloId", unique = true, updatable = false, nullable = false)
        private Articulo articulo;

        public Valoracion() {
        }

        public Valoracion(boolean tipo, Usuario usuario, Articulo articulo) {
            this.tipo = tipo;
            this.usuario = usuario;
            this.articulo = articulo;
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

        public Articulo getArticulo() {
            return articulo;
        }

        public void setArticulo(Articulo articulo) {
            this.articulo = articulo;
        }
    }
