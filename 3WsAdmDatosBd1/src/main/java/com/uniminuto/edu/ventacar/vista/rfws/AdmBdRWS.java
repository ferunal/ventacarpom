/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.ventacar.vista.rfws;

import com.uniminuto.edu.ventacar.ctrl.AutoCRUD;
import com.uniminuto.edu.ventacar.modelo.CaractXAuto;
import com.uniminuto.edu.ventacar.modelo.CmtCalificacion;
import com.uniminuto.edu.ventacar.modelo.VntCaracteristicas;
import com.uniminuto.edu.ventacar.modelo.VntCarro;
import com.uniminuto.edu.ventacar.modelo.VntTipocrt;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author fercris
 */
@Stateless
@Path("/admdatosbd")
public class AdmBdRWS extends BaseRestfulWS {

    AutoCRUD acrud = new AutoCRUD();

    @PUT
    @Path("/addauto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addAuto(@QueryParam("carro") VntCarro carro) {
//        acrud.setMds(dsMyUsuarios);
        acrud.setPgDs(dsPgVentacar);
       
        return String.valueOf(acrud.guardarCarro(carro));
    }

    @GET
    @Path("/getautos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VntCarro> getLstCarros() {
//        acrud.setMds(dsMyUsuarios);
        acrud.setPgDs(dsPgVentacar);
        return acrud.getLstCarros();
    }

    @GET
    @Path("/getcalxauto")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CmtCalificacion> getCalificacionsXauto(@QueryParam("pCarId") Long pCarId) {
//        acrud.setMds(dsMyUsuarios);
        acrud.setPgDs(dsPgVentacar);
        return acrud.getCmtCalificacionsXauto(pCarId);
    }

    @GET
    @Path("/getcarcactxauto")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VntCaracteristicas> getLstCaractXAuto(@QueryParam("pCarId") Long pCarId) {
//        acrud.setMds(dsMyUsuarios);
        acrud.setPgDs(dsPgVentacar);
        return acrud.cargarCaractsXAuto(pCarId);
    }

    @GET
    @Path("/getcarcact")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VntCaracteristicas> getLstCaract() {
//        acrud.setMds(dsMyUsuarios);
        acrud.setPgDs(dsPgVentacar);
        return acrud.cargarCaracts();
    }

    @PUT
    @Path("/addcaract")
    @Produces(MediaType.TEXT_PLAIN)
    public String addCaract(@QueryParam("strCaract") String strCaract, @QueryParam("tipoCar") Integer tipoCar) {
//        acrud.setMds(dsMyUsuarios);
        acrud.setPgDs(dsPgVentacar);
        return String.valueOf(acrud.guardarCaracteristica(strCaract, tipoCar));
    }

    @POST
    @Path("/addcarxauto")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public String guardarCaractXAuto(@QueryParam("pVntCarcactxautos") CaractXAuto[] pVntCarcactxautos) {
//        acrud.setMds(dsMyUsuarios);
        acrud.setPgDs(dsPgVentacar);
        return String.valueOf(acrud.guardarCaracXAuto(Arrays.asList(pVntCarcactxautos)));
    }

    @GET
    @Path("/gettipocaract")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VntTipocrt> getLstTipocrts() {
//        acrud.setMds(dsMyUsuarios);
        acrud.setPgDs(dsPgVentacar);
        return acrud.cargarTipoCaract();
    }

}
