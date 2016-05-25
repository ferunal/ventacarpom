/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uniminuto.edu.clnadmdatosusrautos;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniminuto.edu.clncompautos.wscln.AdmDatosUsrRFWSCln;
import com.uniminuto.edu.ventacar.modelo.Uusario;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author fercris
 */
@SessionScoped
@Named
public class AdmDatosUsrJSFBean implements Serializable {

    private Uusario uusario = new Uusario();
    AdmDatosUsrRFWSCln adurfwsc = new AdmDatosUsrRFWSCln();

    public String validarUsuarioAE() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            Uusario u = adurfwsc.validarUsuario(Uusario.class, mapper.writeValueAsString(uusario));
            if (u.getUsrId() != null) {
                return "ingresar";
            } else {
                return "";
            }

        } catch (JsonProcessingException ex) {
            Logger.getLogger(AdmDatosUsrJSFBean.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    /**
     * @return the uusario
     */
    public Uusario getUusario() {
        return uusario;
    }

    /**
     * @param uusario the uusario to set
     */
    public void setUusario(Uusario uusario) {
        this.uusario = uusario;
    }
}
