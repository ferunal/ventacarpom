<?php
    header("P3P: CP=\"ALL DSP COR PSAa PSDa OUR NOR ONL UNI COM NAV\"");
    ini_set('date.timezone', 'America/Bogota');
    ini_set('E_ERROR', 1);
    require_once('nusoap/lib/nusoap.php');
    $cliente = new nusoap_client('http://localhost/webService/returnArray.php?wsdl', 'WSDL');
		$cliente->http_encoding='utf-8';
		$cliente->defencoding='utf-8';
		$cliente->decode_utf8 = false;
		$resultado = $cliente->call('RetornarAutos', array('id' => 3));
    echo "<pre>";
    $respuesta = $resultado['magico'];
    echo $respuesta;
    print_r(json_decode($respuesta));
    echo "</pre>";
