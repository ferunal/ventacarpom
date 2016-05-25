/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.clncompautos.wscln;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:AdmUsuarioRWS [/admusr]<br>
 * USAGE:
 * <pre>
 *        AdmDatosUsrRFWSCln client = new AdmDatosUsrRFWSCln();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author fercris
 */
public class AdmDatosUsrRFWSCln {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:38080/7WsAdmDAtosBd/webresources";

    public AdmDatosUsrRFWSCln() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("admusr");
    }

    public <T> T validarUsuario(Class<T> responseType, String usr) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (usr != null) {
            resource = resource.queryParam("usr", usr);
        }
        resource = resource.path("addusr");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getLstUusarios(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getusr");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String actUsuario(Object requestEntity) throws ClientErrorException {
        return webTarget.path("actusr").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public String addUsuario(Object requestEntity) throws ClientErrorException {
        return webTarget.path("addusr").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public void close() {
        client.close();
    }

}
