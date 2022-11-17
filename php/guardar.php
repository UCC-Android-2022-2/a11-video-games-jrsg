<?php
include 'bd.php';

$retorno = array(
    'exito' => false,
    'mensaje' => 'N/A'
);

$post = empty($_POST) ? json_decode(file_get_contents('php://input'), true) : $_POST; //ajusta a $_GET a necesidad

$titulo         = $post['titulo'];
$precio         = $post['precio'];

$xbox           = $post['xbox'];
$playstation    = $post['playstation'];
$nintendo       = $post['nintendo'];
$pc             = $post['pc'];

$estado         = $post['estado'];


if( empty($post['id']) ){
    $insert = "insert into games values (null, '$titulo', $precio, $xbox, $playstation, $nintendo, $pc, '$estado', NOW(), null)";
}else{
    $insert = "update games set titulo = '$titulo', 
                precio = $precio, 
                xbox = $xbox, 
                playstation = $playstation, 
                nintendo = $nintendo, 
                pc = $pc, 
                estado = '$estado', 
                actualizacion = NOW()
                where id = $post[id]";
}

$resultado = mysqli_query($conexion, $insert);

if($resultado){
    $retorno['exito']   = true;
    $retorno['mensaje'] = 'Guardado correctamente';
}else{
    $retorno['mensaje'] = 'Error en BD';
}

header('Content-type: application/json');
echo json_encode( $retorno );
?>