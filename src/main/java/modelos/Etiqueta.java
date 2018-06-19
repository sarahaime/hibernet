package modelos;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Etiqueta {

    @Id
    @GeneratedValue
    private long id;


    private String etiqueta;

    public Etiqueta(){};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

}
