/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.clncompautos.jsf;

import com.uniminuto.edu.clncompautos.wscln.AdmDatosRFWSCln;
import com.uniminuto.edu.clncompautos.wscln.CompAutosWS;
import com.uniminuto.edu.clncompautos.wscln.CompAutosWS_Service;
import com.uniminuto.edu.clncompautos.wscln.VntCarro;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author fercris
 */
@SessionScoped
@Named
public class CompararAutosJSFBean implements Serializable {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_38080/2WsCompAutos/CompAutosWS.wsdl")
    private CompAutosWS_Service service;

    AdmDatosRFWSCln adrfwsc = new AdmDatosRFWSCln();
    private List<TablaAuto> lstTablaAutos = new ArrayList<>();

    @PostConstruct
    public void init() {
        cargarAutos();
    }

    public void compararAutosSel_AE() {
        CompAutosWS port = service.getCompAutosWSPort();
        String strAutos = lstTablaAutos.stream().
                filter(e -> e.isSeleccionado()).
                map(e -> e.getVntCarro().getCarId().toString()).
                collect(Collectors.joining(","));
        List<VntCarro> lstVntCarros = port.compararAutos(strAutos);
        adrfwsc.addCaract();
        

    }

    public void cargarAutos() {
        CompAutosWS port = service.getCompAutosWSPort();
        lstTablaAutos = port.getLstCarros().stream().map(e -> new TablaAuto(e)).collect(Collectors.toList());
    }

    /**
     * @return the lstTablaAutos
     */
    public List<TablaAuto> getLstTablaAutos() {
        return lstTablaAutos;
    }

    /**
     * @param lstTablaAutos the lstTablaAutos to set
     */
    public void setLstTablaAutos(List<TablaAuto> lstTablaAutos) {
        this.lstTablaAutos = lstTablaAutos;
    }

}
