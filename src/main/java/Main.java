import modelos.Etiqueta;
import modelos.Usuario;
import servicios.*;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.SQLException;
import java.util.Set;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;

public class Main {
    public static void main(String[] args)throws SQLException {
        //Iniciando el servicio
        BootStrapServices.startDb();

        //Prueba de Conexión.
        DB.getInstancia().testConexion();

        //indicando los recursos publicos, con esto se puede acceder a ellos sin hacerle metodos get ni post ni nada de eso
        staticFiles.location("/templates");


        BootStrapServices.crearTablas();

        //usuario administrador por defecto
        UsuarioServices usuarioServices = new UsuarioServices();
        if(usuarioServices.listaUsuarios().size() < 1){
            Usuario administrador = new Usuario();
            administrador.setNombre("Nombre del Administrador");
            administrador.setUsername("admin");
            administrador.setAdministrador(true);
            administrador.setAutor(true);
            administrador.setPassword("admin");
            if( usuarioServices.crearUsuario(administrador)){
                System.out.println("Usuario administrador creado..");
            }
        }

//        ArticuloServices as = new ArticuloServices();
//        as.crearArticulo("El agua post", "cuerpesito", 1);

//        EtiquetaServices es = new EtiquetaServices();
//        es.crearEtiqueta("COCINA");
//        es.crearEtiqueta("PROGRAMACION");
//        es.crearEtiqueta("YOLO");
//        es.crearEtiqueta("FOTOGRAFIA");
//        es.crearEtiqueta("TECNOLOGIA");
//        es.crearEtiqueta("REDES");
//        es.crearEtiqueta("HTML");
//        es.crearEtiqueta("FINANZAS");
//        es.crearEtiqueta("RESTAURANTES");

        //Seteando el puerto en Heroku
        port(getHerokuAssignedPort());

        //Las rutas
        new ManejoRutas().rutas();

        //Aplicando los filtros
        new Filtros().aplicarFiltros();

        new ManejoExcepciones().manejoExcepciones();

        new CookieYSesiones().cookieSesiones();

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}

