<%@page import="cliente.Cliente"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

        <title>Google</title>

        <!-- Bootstrap core CSS -->
        <link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">


    </head>

    <body>

        <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Buscador paralelo</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="index.jsp">Buscar</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <br><br><br>

        <div class="container">
            <div class="text-center">
                <form id="search" action="search.jsp" method="get">
                    <input title="Ingrese una búsqueda" type="text" name="search" id="search" required /><br/>
                    <input value="Buscar" type="submit" />
                </form>

            </div>
        </div><!-- /.container -->

    </body>
</html>
