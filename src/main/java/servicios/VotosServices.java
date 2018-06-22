package servicios;

import modelos.Votos;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VotosServices extends GestionDb<Votos> {

    private VotosServices() {
        super(Votos.class);
    }


    public Object getVotoUsuarioEnArticulo(long articuloID, long usuarioID) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "select votos from Votos votos where votos.articulo.id = :votos_articuloID and votos.usuario.id = :votos_usuarioID"
            );

            query.setParameter("votos_articuloID", articuloID);
            query.setParameter("votos_usuarioID", usuarioID);
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Votos> getVotosPorArticulo(long articuloID, String tipoVotos) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "select votos from Votos votos where votos.articulo.id = :varticuloID and votos.votos = :tipoVotos"
            );

            query.setParameter("articuloID", articuloID);
            query.setParameter("tipoVotos", tipoVotos);
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
}


