package servicios;
 import modelos.Valoracion;
 import modelos.Usuario;

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

 public ValoracionServices() {
  super(Valoracion.class);
 }
}




