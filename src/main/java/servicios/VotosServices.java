package Servicios;

import Modelos.Votos;
import servicios.GestionDb;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VotosServices extends GestionDb<Votos> {
    private static VotosServices instancia;

    private VotosServices() {
        super(Votos.class);
    }

    public static VotosServices getInstancia() {
        if (instancia == null) {
            instancia = new VotosServices();
        }

        return instancia;
    }

    public Object encontrarVotoUsuarioEnArticulo(long articuloID, long usuarioID) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "from Votos votos where votos.articulo.id = :votos_articuloID and votos.usuario.id = :votos_usuarioID"
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

    public List<Votos> encontrarVotosPorArticulo(long articuloID, String tipoVotos) {
        EntityManager em = getEntityManager();

        try {
            Query query = em.createQuery(
                    "from Votos votos where votos.articulo.id = :votos_articuloID and votos.votos = :votos_tipoVotos"
            );

            query.setParameter("votos_articuloID", articuloID);
            query.setParameter("votos_tipoVotos", tipoVotos);
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
}