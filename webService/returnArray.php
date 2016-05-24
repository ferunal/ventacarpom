<?php

include("nusoap/lib/nusoap.php");
// Create the server instance
$hosname = "localhost";
$baseDeDatos = "carros";
$usuarioDB = "root";
$passwordDB = "";
$tabla = "auto";
$campoBusqueda = "mail";
$server = new soap_server();
// Initialize WSDL support
$server->configureWSDL('autoswsdl', 'urn:autoswsdl');
// Register the method to expose
$server->wsdl->addComplexType(
        'autoInfo', 'complextType', 'struct', 'all', '', array(
    'magico' => array('name' => 'magico', 'type' => 'xsd:string')
        )
);
//$server->wsdl->addComplexType(
//        'ContactArray', 'complexType', 'array', '', 'SOAP-ENC:Array', array(), array(
//    array('ref'=>'SOAP-ENC:arrayType', 'wsdl:arrayType' => 'tns:autoInfo[]')
//        ), 'tns:autoInfo'
//);
$server->register('RetornarAutos', // method name
        array('id' => 'xsd:int'), // input parameters
        array('return' => 'tns:autoInfo'), // output parameters
        'urn:autowsdl', // namespace
        'urn:autowsdl#retornarAutos', // soapaction
        'rpc', // style
        'encoded', // use
        'Hola bienvenido'                     // documentation
);

// Define the method as a PHP function
function ValidarEmail($email) {
  global $hosname;
  global $baseDeDatos;
  global $usuarioDB;
  global $passwordDB;
  global $tabla;
  global $campoBusqueda;
  try {
    $conn = new PDO("mysql:host=$hosname;dbname=$baseDeDatos", "$usuarioDB", "$passwordDB");
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = $conn->prepare("SELECT uid,mail,name FROM $tabla WHERE $campoBusqueda = :Email");
    $sql->execute(array('Email' => $email));
    $resultado = $sql->rowCount();
  } catch (PDOException $e) {
    echo "ERROR: " . $e->getMessage();
  }
  if ($resultado < 1) {
    return false;
  } else {
    $datos = $sql->fetch(PDO::FETCH_ASSOC);
    return $datos;
  }
}

function RetornarAutos($id) {
  global $hosname;
  global $baseDeDatos;
  global $usuarioDB;
  global $passwordDB;
  global $tabla;
  global $campoBusqueda;
  try {
    $params = array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8");  
    $conn = new PDO("mysql:host=$hosname;dbname=$baseDeDatos", "$usuarioDB", "$passwordDB",$params);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $sql = $conn->prepare("call datos_auto(:idAuto)");
    $sql->execute(Array('idAuto' => $id));
    $resultado = Array();
    while($respuesta = $sql->fetch(PDO::FETCH_ASSOC)){
        $resultado['foto']=$respuesta['foto'];
        $resultado[$respuesta['descripcion']][]=Array(($respuesta['nombre']),htmlentities($respuesta['descAuto']));
        //array_push($resultado,$respuesta);
      }
    $registro = $sql->rowCount();
    if ($registro >= 1) {
      $magia = array();
      $magico =  json_encode($resultado);
      $magia['magico']=$magico;
      return $magia;
    } else {
      return false;
    }
  } catch (PDOException $e) {
    echo "ERROR: " . $e->getMessage();
  }
  if ($resultado < 1) {
    return false;
  } else {
    $datos = $sql->fetch(PDO::FETCH_ASSOC);
    return $datos;
  }
}
//echo "<br><pre>";
//print_r(RetornarAutos(3));
//function helloDonAndres($algo){
//				return 'Hello, Don ' . $name;
//}
// Use the request to (try to) invoke the service
$HTTP_RAW_POST_DATA = isset($HTTP_RAW_POST_DATA) ? $HTTP_RAW_POST_DATA : '';
$server->service($HTTP_RAW_POST_DATA);
