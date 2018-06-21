<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Blog Post</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="vendor/bootstrap/css/blog-post.css" rel="stylesheet">

</head>

<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">Blog</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/home">Home
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item" th:if="${registeredUser.id}">
                    <a class="nav-link" href="/logout"> <i class="fa fa-fw fa-sign-out"></i>Salir</a>
                </li>
                <li class="nav-item"  th:unless="${registeredUser.id}">
                    <a class="nav-link" href="/login.html"><i class="fa fa-fw fa-sign-in"></i>Iniciar sesion</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/registrar" th:if="${registeredUser.administrador}"></i>Registrar</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/articuloForm.html" th:if="${registeredUser.autor}"></i>Postear</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color:blanchedalmond" href="/home" th:href="|/articulos?usuarioid=${registeredUser.id}|"  th:text="${registeredUser.username}" ></i></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Post Content Column -->
        <div class="col-lg-8">

            <!-- Title -->
            <h1 class="mt-4" th:text="${articulo.titulo}" >Post Title</h1>

            <!-- Author -->
            <p class="lead">
                Por:
                <a href="#" th:text="${autor}">Sarahaimep</a>
            </p>

            <hr>

            <!-- Date/Time -->
            <p th:text="${articulo.fecha}">Posted on January 1, 2018 at 12:00 PM</p>

            <hr>

            <!-- Post Content -->
            <p class="lead" th:text="${articulo.cuerpo}">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus, vero, obcaecati, aut, error quam sapiente nemo saepe quibusdam sit excepturi nam quia corporis eligendi eos magni recusandae laborum minus inventore?</p>

            <hr>

            <!-- Comments Form -->
            <div class="card my-4">
                <h5 class="card-header">Dejar comentario:</h5>
                <div class="card-body">
                    <form method="post" action="/comentar">
                        <div class="form-group">
                            <textarea class="form-control" rows="3" name="comentario"></textarea>
                        </div>
                        <input type="hidden" name="articuloid" th:value="${articulo.id}">
                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </form>
                </div>
            </div>

            <!-- Comments -->
            <div  th:each="comentario:${comentarios}">
                <div class="media mb-4">
                    <img class="d-flex mr-3 rounded-circle" src="http://placehold.it/50x50" alt="">
                    <div class="media-body">
                        <h5 class="mt-0" th:text="${comentario.autor.nombre}">Commenter Name</h5>
                        <p th:text="${comentario.comentario}"> </p>
                    </div>
                </div>
            </div>

        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

            <!--&lt;!&ndash; Search Widget &ndash;&gt;-->
            <!--<div class="card my-4">-->
            <!--<h5 class="card-header">Search</h5>-->
            <!--<div class="card-body">-->
            <!--<div class="input-group">-->
            <!--<input type="text" class="form-control" placeholder="Search for...">-->
            <!--<span class="input-group-btn">-->
            <!--<button class="btn btn-secondary" type="button">Go!</button>-->
            <!--</span>-->
            <!--</div>-->
            <!--</div>-->
            <!--</div>-->

            <!-- Etiquetas Widget -->
            <div class="card my-4">
                <h5 class="card-header">Etiquetas</h5>
                <div class="card-body">
                    <div class="row">
                        <div class="col-lg-6">
                            <ul class="list-unstyled mb-0" th:each="etiqueta:${etiquetas}">
                                <li>
                                    <a href="#" th:text="${etiqueta.etiqueta}">Web Design</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!--&lt;!&ndash; Side Widget &ndash;&gt;-->
        <!--<div class="card my-4">-->
        <!--<h5 class="card-header">Side Widget</h5>-->
        <!--<div class="card-body">-->
        <!--You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!-->
        <!--</div>-->
        <!--</div>-->

    </div>

</div>

<h5 class="col-12 pt-3">
    <strong><i class="fas fa-eye"></i> REACCIONES</strong>
    <hr noshade>
</h5>
<form action="/articulo/${articulo.id}/votar" method="post" class="mx-auto pb-3 pl-3">
    <div class="form-check form-check-inline">
        <input class="form-check-input" type="radio" name="voto" value="Me gusta">
        <label class="form-check-label" for="me-gusta">
            <i class="fas fa-thumbs-up fa-2x" style="color: #0099ff" title="Me gusta"></i>
            <span class="badge badge-secondary">${votosMeGusta}</span>
        </label>
    </div>
    <!-- /.row -->

</form>

<div class="form-check form-check-inline">
    <input class="form-check-input" type="radio" name="voto" value="Me disgusta">
    <label class="form-check-label" for="me-disgusta">
        <i class="fas fa-frown fa-2x" style="color: red" title="Me disgusta"></i>
        <span class="badge badge-secondary">${votosMeDisgusta}</span>
    </label>
</div>

<button type="submit" class="btn btn-outline-dark">Reaccionar</button>
<br><br>
  <#if votoActual??>
  <p class="mb-0"><strong>Has votado por:</strong> ${votoActual.voto}.</p>
  <#else>
  <strong>Aún no has dejado tu voto en este artículo.</strong>
  </#if>
</form>
</div>
</div>

//para la valoracion
<div class="card-footer p-2">
    <strong class="text-primary m-0">
        <i class="far fa-comment"></i> ${comentario.comentario}
    </strong>
    <form action="/articulo/${comentario.id}/valorar" method="post"
          class="mx-auto pb-3 pl-3">
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="valoracion" value="Me gusta">
            <label class="form-check-label" for="me-gusta">
                <i class="fas fa-thumbs-up fa-2x" style="color: #0099ff"
                   title="Me gusta"></i>
                <span class="badge badge-secondary">${comentario.cantidadMeGusta}</span>
            </label>
        </div>

        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="valoracion"
                   value="Me disgusta">
            <label class="form-check-label" for="me-disgusta">
                <i class="fas fa-frown fa-2x" style="color: red" title="Me disgusta"></i>
                <span class="badge badge-secondary">${comentario.cantidadMeDisgusta}</span>
            </label>
        </div>

        <button type="submit" class="btn btn-outline-dark">Reaccionar</button>
    </form>
</div>
<!-- /.container -->

<!-- Footer -->
<footer class="py-5 bg-dark">
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; Internet Avanzado 2018</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
