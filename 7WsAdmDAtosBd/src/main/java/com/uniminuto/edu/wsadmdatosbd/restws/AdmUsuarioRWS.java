/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.wsadmdatosbd.restws;

import com.uniminuto.edu.ventacar.ctrl.UsuarioCRUD;
import com.uniminuto.edu.ventacar.modelo.Uusario;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author fercris
 */
@Path("/admusr")
@Stateless
public class AdmUsuarioRWS extends BaseRestfulWS {

    UsuarioCRUD ucrud = new UsuarioCRUD();

    @GET
    @Path("getusr")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Uusario> getLstUusarios() {
        ucrud.setMds(dsMyUsuarios);
       
        return ucrud.cargarUsuarios();
    }

}
