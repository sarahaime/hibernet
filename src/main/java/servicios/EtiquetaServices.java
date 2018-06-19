package servicios;

import modelos.Articulo;
import modelos.Etiqueta;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EtiquetaServices extends GestionDb<Etiqueta>{

    public EtiquetaServices(){
        super(Etiqueta.class);
    }

    public boolean crearEtiqueta(String etiqueta){
        Etiqueta et = new Etiqueta();
        et.setEtiqueta(etiqueta);
        crear( et );
        return true;
    }

    public List<Etiqueta> getEtiquetaByArticuloID(long articuloID){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a from Articulo a where a.id =:id");
        query.setParameter("id", articuloID);
        List<Articulo> articulos = query.getResultList();
        if(articulos.size() > 0){
            return new ArrayList<>(articulos.get(0).getEtiquetas());
        }
        return new ArrayList<>();
    }

    public boolean agregarEtiquetaArticulo(long etiquetaID, long articuloID){
        Articulo articulo = new ArticuloServices().find(articuloID);
        Etiqueta etiqueta = find(etiquetaID);
        articulo.agregarEtiqueta( etiqueta );
        new ArticuloServices().editar(articulo);

        return true;
    }



    /******************************************************************/
    public static Etiqueta getEtiquetaByID(long id){
        Etiqueta etiqueta = null;
        Connection con = null;
        try {
            //utilizando los comodines (?)...
            String query = "select * from ETIQUETA where id = ?";
            con = DB.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setLong(1, id);
            //Ejecuto...
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                etiqueta = new Etiqueta();
                etiqueta.setId(rs.getLong("id"));
                etiqueta.setEtiqueta(rs.getString("etiqueta"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return etiqueta;

    }

}
