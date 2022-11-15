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
    
    $indice = 0;
    while($fila = mysqli_fetch_assoc($resultado)){
        
        $games[$indice]["id"]       = (int) $fila['id'];
        $games[$indice]["nombre"]   = $fila['titulo'];
        $games[$indice]["estado"]   = $fila['estado'];
        
        $games[$indice]["precio"]   = (float) $fila['precio'];
        
        $games[$indice]["xbox"]             = $fila['xbox'] == 1;
        $games[$indice]["playstation"]      = $fila['playstation'] == 1;
        $games[$indice]["nintendo"]         = $fila['nintendo'] == 1;
        $games[$indice]["pc"]               = $fila['pc'] == 1;
        
        $indice++;
    }
    
    $retorno['lista'] = $games;
}else{
    $retorno['mensaje'] = 'Error en BD';
}

header('Content-type: application/json');
echo json_encode($retorno);
?>