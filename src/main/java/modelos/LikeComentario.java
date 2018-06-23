package modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class LikeComentario implements Serializable {


    @Id
    private int comentarioId;

    @Id
    private int usuarioId;

    private int val;

    public LikeComentario() {}

    public int getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(int comentarioId) {
        this.comentarioId = comentarioId;
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
