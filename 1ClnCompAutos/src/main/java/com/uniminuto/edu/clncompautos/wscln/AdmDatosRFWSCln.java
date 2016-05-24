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
 * Jersey REST client generated for REST resource:AdmBdRWS [/admdatosbd]<br>
 * USAGE:
 * <pre>
 *        AdmDatosRFWSCln client = new AdmDatosRFWSCln();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author fercris
 */
public class AdmDatosRFWSCln {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:38080/3WsAdmDatosBd1/webresources";

    public AdmDatosRFWSCln() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("admdatosbd");
    }

    public String addAuto(Object requestEntity) throws ClientErrorException {
        return webTarget.path("addauto").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public <T> T getLstCaractXAuto(Class<T> responseType, String pCarId) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (pCarId != null) {
            resource = resource.queryParam("pCarId", pCarId);
        }
        resource = resource.path("getcarcactxauto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getLstCaract(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getcarcact");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getCalificacionsXauto(Class<T> responseType, String pCarId) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (pCarId != null) {
            resource = resource.queryParam("pCarId", pCarId);
        }
        resource = resource.path("getcalxauto");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getLstTipocrts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("gettipocaract");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String addCaract() throws ClientErrorException {
        return webTarget.path("addcaract").request().put(null, String.class);
    }

    public String guardarCaractXAuto(Object requestEntity) throws ClientErrorException {
        return webTarget.path("addcarxauto").request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), String.class);
    }

    public <T> T getLstCarros(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getautos");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
