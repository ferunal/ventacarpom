/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.wscompautos.ws.soap;

import com.uniminuto.edu.ventacar.ctrl.AutoComparar;
import com.uniminuto.edu.ventacar.modelo.VntCarro;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author fercris
 */
@WebService(serviceName = "CompAutosWS")
public class CompAutosWS extends BaseSOAPWS {

    AutoComparar ac = new AutoComparar();

    /**
     * This is a sample web service operation
     *
     * @param txt
     * @return
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    @WebMethod(operationName = "compararAutos")
    public List<VntCarro> getLstCarrosXIds(@WebParam(name = "pIdCarros") String pIdCarros) {        
        ac.setPgDs(dsPgVentacar);
        return ac.getLstCarrosXIds(pIdCarros);
    }

    @WebMethod(operationName = "getLstCarros")
    public List<VntCarro> getLstCarros() {        
        ac.setPgDs(dsPgVentacar);
        return ac.getLstCarros();
    }
}
