<?php
include 'bd.php';

$retorno = array(
    'exito' => false,
    'mensaje' => 'N/A',
    'lista' => array()
);

//$estado = 'N'; //get???

$sql = "select * from games";

if(!empty($estado)){
    $sql .= " where estado = '$estado'";
}

$resultado = mysqli_query($conexion, $sql);

if($resultado){
    $retorno['exito'] = true;
    
    $games = array();
    
    while($fila = mysqli_fetch_assoc($resultado)){
        $games[] = $fila;
    }
    
    $retorno['lista'] = $games;
}else{
    $retorno['mensaje'] = 'Error en BD';
}

header('Content-type: application/json');
echo json_encode($retorno);
?>