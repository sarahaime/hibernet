package servicios;

import modelos.Articulo;
import modelos.Etiqueta;
import modelos.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.min;

public class ArticuloServices extends GestionDb<Articulo>{

    public ArticuloServices(){
        super(Articulo.class);
    }

    public List<Articulo> listaArticulos() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select a from Articulo a");
        List<Articulo> lista = query.getResultList();
        em.close();
        return lista;
    }

    public Articulo getArticulo(long id) {
        return find(id);
    }

    public boolean crearArticulo(String titulo, String cuerpo, long usuarioid, String tags){
        Articulo articulo = new Articulo();
        articulo.setAutor( new UsuarioServices().find(usuarioid) );
        articulo.setTitulo(titulo);
        articulo.setCuerpo(cuerpo);
        articulo.setEtiquetas(generarEtiquetas(tags));
        crear(articulo);
        return true;
    }


    //Actualizar los Articulos.
    public boolean actualizarArticulo(String titulo, String cuerpo, long id, String tags){
        Articulo articulo = find(id);
        articulo.setTitulo(titulo);
        articulo.setCuerpo(cuerpo);
        articulo.setEtiquetas(generarEtiquetas(tags));
        crear(articulo);

        return true;
    }

    /***************************************FALTA*************************************/
    //Metodo borrar los Articulos
    public boolean borrarArticulo(long id, long usuarioid){
        boolean ok =false;

        Connection con = null;
        try {
            //que sea quien lo creo, o un administrador
            String query = "delete from Articulo where id = ? and (USUARIOID = ? or exists (select* from USUARIO where id = ? and administrador)) ";
            con = DB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setLong(1, id);
            prepareStatement.setLong(2, usuarioid);
            prepareStatement.setLong(3, usuarioid);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    private boolean agregarTags(long articuloid, String tags){
        boolean ok =false;

        List<String> tagsList = Arrays.asList(tags.split(",[ ]*"));

        Connection con = null;
        try {
            //que sea quien lo creo, o un administrador
            String query = "delete from ETIQUETA " +
                    " where ARTICULOID = ?";
            con = DB.getInstancia().getConexion();

            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setLong(1, articuloid);

            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;
            List<String> ets = new ArrayList<String>();
            for(String etiqueta : tagsList) {
               boolean enLista = false;
               for(String  et : ets){
                   if( et.equalsIgnoreCase(etiqueta)  ){
                       enLista = true;
                   }
               }
               if(!enLista) ets.add( etiqueta );
            }

            for(String etiqueta : ets) {
                query = "insert into ETIQUETA(ARTICULOID, ETIQUETA) values(?,?)";
                prepareStatement = con.prepareStatement(query);
                //Antes de ejecutar seteo los parametros
                prepareStatement.setLong(1, articuloid);
                prepareStatement.setString(2, etiqueta.toUpperCase());
                prepareStatement.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;



    }

    private Set<Etiqueta>generarEtiquetas(String etiquetas){
        List<String> tagsList = Arrays.asList(etiquetas.split(",[ ]*"));
        Set<Etiqueta> ans = new HashSet<>();

        EtiquetaServices es = new EtiquetaServices();

        for (String tagName : tagsList){
            ans.add(  es.getEtiquetaByName( tagName ) );
        }

        return ans;
    }

/*
    public boolean borrarArticulo(int id){
        boolean ok =false;

        Connection con = null;
        try {

            String query = "delete from Articulo where id = ?";
            con = DB.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = con.prepareStatement(query);

            //Indica el where...
            prepareStatement.setInt(1, id);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }*/

}
