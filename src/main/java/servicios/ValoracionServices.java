package servicios;
import modelos.Valoracion;


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

public class ValoracionServices extends GestionDb<Valoracion> {


 private static ValoracionServices instancia;


 public ValoracionServices() {
  super(Valoracion.class);
 }


 public static ValoracionServices getInstancia() {
  if (instancia == null) {
   instancia = new ValoracionServices();
  }
  return instancia;
 }

 public Object encontrarValoracionUsuarioEnComentario(long comentarioID, long usuarioID) {
  EntityManager em = getEntityManager();

  try {
   Query query = em.createQuery(
           "from Valoracion valoracion where valoracion.comentario.id = :valoracion_comentarioID and valoracion.usuario.id = :valoracion_usuarioID"
   );
   query.setParameter("valoracion_comentarioID", comentarioID);
   query.setParameter("valoracion_usuarioID", usuarioID);
   return query.getSingleResult();
  } catch (Exception ex) {
   return null;
  } finally {
   em.close();
  }
 }

 public List<Valoracion> encontrarValoracionesPorComentario(long comentarioID, String tipoValoracion) {
  EntityManager em = getEntityManager();

  try {
   Query query = em.createQuery(
           "from Valoracion valoracion where valoracion.comentario.id = :valoracion_comentarioID and valoracion.tipo = :valoracion_tipoValoracion"
   );

   query.setParameter("valoracion_comentarioID", comentarioID);
   query.setParameter("valoracion_tipoValoracion", tipoValoracion);

   return query.getResultList();
  } catch (Exception ex) {
   return null;
  } finally {
   em.close();
  }
 }
}






