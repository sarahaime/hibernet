package modelos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class LikeArticulo implements Serializable {

    @Id
    private int articuloId;

    @Id
    private int usuarioId;

    private int val;

    public LikeArticulo() {}



    public int getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
