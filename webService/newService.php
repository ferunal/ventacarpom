<?php

/*
 * This function takes an $email address and returns an array of email addresses
 * that are the given email address's recent contacts from their address book.
 *
 * @param string $email (the email address of the user)
 * @param string $token (a password that is used for authentication for use of this function, it is NOT the email users password.)
 */

function getAutocompleteContacts($email, $num, $token) {
  if ($token != SOAP_TOKEN)
    return new soap_fault('Server', '', 'Supplied token does not match our records', '');

  $email = strtolower(trim($email));

  if (!ereg('^' . email_reg . '$', $email))
    return new soap_fault('Client', '', 'Must supply a valid email address: $email not valid', '');

  $aDB = DB::connect(ADDR_DSN);
  if (DB::isError($aDB)) {
    return new soap_fault('Server', '', 'Service temporarily unavailable: could not connect to ADDR_DSN DB', '');
  }
  $aDB->setFetchMode(DB_FETCHMODE_ASSOC);

// REALLY YOU SHOULD DO YOU'RE OWN QUERY, BUT FOR THE SAKE
// OF THIS EXAMPLE, I'M JUST GOING TO INJECT A COUPLE RESULTS:
  $result = array();
  $result[] = array('contact' => 'Chaos Captain', 'email' => 'choas@sdfusidfousdf.com');
  $result[] = array('contact' => 'Joe Joe', 'email' => 'choas@sdf768sdf798s7df987.com');

  return $result;

#return new soap_fault('Server', '', 'Fallthrough error, should have faulted on invalid type above','');
}

$HTTP_RAW_POST_DATA = isset($HTTP_RAW_POST_DATA) ? $HTTP_RAW_POST_DATA : '';
$server = new soap_server;
$server->configureWSDL('addressbook1', 'urn:' . $_SERVER['SCRIPT_URI']);
$server->wsdl->addComplexType(
        'Contact', 'complexType', 'struct', 'all', '', array(
    'contact' => array('name' => 'contact', 'type' => 'xsd:string'),
    'email' => array('name' => 'email', 'type' => 'xsd:string'),
        )
);

$server->wsdl->addComplexType(
        'ContactArray', 'complexType', 'array', '', 'SOAP-ENC:Array', array(), array(
    array('ref' => 'SOAP-ENC:arrayType', 'wsdl:arrayType' => 'tns:Contact[]')
        ), 'tns:Contact'
);

$server->register('getAutocompleteContacts', array('email' => 'xsd:string', 'num' => 'xsd:int', 'token' => 'xsd:string'), // input parameters
        array('return' => 'tns:ContactArray'), 'urn:' . $_SERVER['SCRIPT_URI'], // namespace
        'urn:' . $_SERVER['SCRIPT_URI'] . '', #getAutocompleteContacts'', // soapaction
        'rpc', // style
        'encoded', // use
        'Fetch array of address book contacts for use in autocomplete'); // documentation
#$server->wsdl->schemaTargetNamespace = $_SERVER['SCRIPT_URI'];
$server->service($HTTP_RAW_POST_DATA);
exit();
?>