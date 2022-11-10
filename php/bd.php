<?php

$host = 'localhost';
$user = 'root';
$password = '';
$database = 'videogames';

$conexion = mysqli_connect($host, $user, $password, $database);

if(!$conexion){
    exit( mysqli_connect_error() );
}

mysqli_set_charset($conexion, 'utf8');

?>